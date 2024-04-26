package it.qbsoftware.business.domain.methodcall.process.set.create;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.CreationIdResolver;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

import java.util.Map;
import java.util.UUID;

import java.time.Instant;
import java.util.HashMap;

public class StandardCreateEmail implements CreateEmail {
    private final EmailRepository emailRepository;
    private final EmailBuilderPort emailBuilderPort;
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final ThreadChangesTrackerRepository threadChangesTrackerRepository;
    private final AccountStateRepository accountStateRepository;
    private final SetErrorEnumPort setErrorEnumPort;

    public StandardCreateEmail(final EmailBuilderPort emailBuilderPort,
            final EmailRepository emailRepository,
            final AccountStateRepository accountStateRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        this.emailRepository = emailRepository;
        this.emailBuilderPort = emailBuilderPort;
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.threadChangesTrackerRepository = threadChangesTrackerRepository;
        this.accountStateRepository = accountStateRepository;
        this.setErrorEnumPort = setErrorEnumPort;
    }

    @Override
    public CreatedResult<EmailPort> create(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException {
        final HashMap<String, EmailPort> created = new HashMap<String, EmailPort>();
        final HashMap<String, SetErrorPort> notCreated = new HashMap<String, SetErrorPort>();

        for (final Map.Entry<String, EmailPort> mailToCreate : setEmailMethodCallPort.getCreate().entrySet()) {

            try {
                EmailPort emailDiff = email(mailToCreate.getValue(), previousResponses,
                        setEmailMethodCallPort.accountId());
                created.put(mailToCreate.getKey(), emailDiff);
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                notCreated.put(mailToCreate.getKey(), setErrorEnumPort.invalidProperties());
                continue;
            }
        }

        return new CreatedResult<>(created, notCreated);
    }

    private EmailPort email(final EmailPort emailPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses, final String accountId)
            throws InvalidArgumentsException, AccountNotFoundException {
        /*
         * NOTA REMOVE ME
         * boldId != emailId, e allo stesso
         */
        final String emailId = accountId + "/" + UUID.randomUUID().toString(); // NOTA Questo è l'id secondo la
                                                                               // specifica JMAP
        final String blobId = accountId + "/" + UUID.randomUUID().toString(); // NOTA Questo è l'id secondo RFC5322
                                                                              // dell'e-mail per i raw
        // email
        // NOTA data
        final String threadId = emailPort.getThreadId() != null
                ? CreationIdResolver.resolveIfNecessary(emailPort.getThreadId(), previousResponses)
                : accountId + "/" + UUID.randomUUID().toString(); // NOTA: funziona veramente così?
        final Long size = 10L; // NOTA: finto
        final Instant recevidAt = Instant.now();
        // final Boolean hasAttachment = false; // NOTA: A prescindere, non rientra tra
        // i requisiti?
        // final String preview = ""; // NOTA: niente preview
        final Map<String, Boolean> mailboxIds = resolveMailboxIdsReference(emailPort.getMailboxIds(),
                previousResponses);

        final EmailBuilderPort emailToSaveBuilder = emailPort.toBuilder();
        emailToSaveBuilder
                .id(emailId)
                .blobId(blobId)
                .threadId(threadId)
                .size(size)
                .mailboxIds(mailboxIds)
                .receivedAt(recevidAt);

        final EmailBuilderPort emailWithOnlyServerSetPropertiesBuilder = emailBuilderPort.reset();
        emailWithOnlyServerSetPropertiesBuilder
                .id(emailId)
                .blobId(blobId)
                .threadId(threadId)
                .size(size)
                .mailboxIds(mailboxIds)
                .receivedAt(recevidAt);

        try {
            emailRepository.save(emailToSaveBuilder.build());
            updateEmailChanges(emailId, accountId);
            updateThread(emailPort.getThreadId(), threadId, accountId);
            updateMailbox(mailboxIds.keySet().toArray(String[]::new), accountId);
        } catch (final SetSingletonException setSingletonException) {
            return email(emailPort, previousResponses, accountId);
        }

        return emailWithOnlyServerSetPropertiesBuilder.build();
    }

    private Map<String, Boolean> resolveMailboxIdsReference(final Map<String, Boolean> mailboxIds,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) throws InvalidArgumentsException {
        if (mailboxIds == null) {
            throw new InvalidArgumentsException();
        }

        final Map<String, Boolean> resolvedIdReference = new HashMap<>();
        for (final Map.Entry<String, Boolean> mailbox : mailboxIds.entrySet()) {
            resolvedIdReference.put(CreationIdResolver.resolveIfNecessary(mailbox.getKey(), previousResponses),
                    mailbox.getValue());
        }

        return resolvedIdReference;
    }

    private void updateEmailChanges(final String emailId, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final EmailChangesTracker emailChangesTracker = emailChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseEmailState();
        emailChangesTracker.emailHasBeenCreated(accountState.emailState(), emailId);

        accountStateRepository.save(accountState);
        emailChangesTrackerRepository.save(emailChangesTracker);
    }

    private void updateThread(final String clientThread, final String threadId, final String accountId)
            throws AccountNotFoundException {
        ThreadChangesTracker threadChangesTracker = threadChangesTrackerRepository.retrive(accountId);
        AccountState accountState = accountStateRepository.retrive(accountId);
        accountState = accountState.increaseThreadState();

        if (clientThread.equals(threadId)) {
            threadChangesTracker = threadChangesTracker.threadHasBeenUpdated(accountState.threadState(), threadId);
        } else {
            threadChangesTracker = threadChangesTracker.threadHasBeenCreated(accountState.threadState(), threadId);
        }

        threadChangesTrackerRepository.save(threadChangesTracker);
        accountStateRepository.save(accountState);
    }

    private void updateMailbox(final String[] mailboxIds, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        // NOTA: mailboxIds == #di?
        for (final String id : mailboxIds) {
            accountState = accountState.increaseEmailState();
            mailboxChangesTracker.mailboxHasBeenUpdated(accountState.mailboxState(), id);
        }

        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
        accountStateRepository.save(accountState);
    }
}