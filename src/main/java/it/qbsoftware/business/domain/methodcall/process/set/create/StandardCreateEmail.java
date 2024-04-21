package it.qbsoftware.business.domain.methodcall.process.set.create;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.SimpleThreadChangesTracker;
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

import com.google.gson.Gson;

import java.time.Instant;
import java.util.HashMap;

//TODO: refactoring e completarlo
public class StandardCreateEmail implements CreateEmail {
    private final EmailRepository emailRepository;
    private final EmailBuilderPort emailBuilderPort;
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final ThreadChangesTrackerRepository threadChangesTrackerRepository;
    private final AccountStateRepository accountStateRepository;
    private final SetErrorEnumPort setErrorEnumPort;
    private final Gson gson;

    public StandardCreateEmail(final EmailBuilderPort emailBuilderPort,
            final EmailRepository emailRepository,
            final AccountStateRepository accountStateRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository,
            final SetErrorEnumPort setErrorEnumPort,
            final Gson gson) {
        this.emailRepository = emailRepository;
        this.emailBuilderPort = emailBuilderPort;
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.threadChangesTrackerRepository = threadChangesTrackerRepository;
        this.accountStateRepository = accountStateRepository;
        this.setErrorEnumPort = setErrorEnumPort;
        this.gson = gson;
    }

    @Override
    public CreatedResult<EmailPort> create(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException {
        final AccountState accountState = accountStateRepository.retrive(setEmailMethodCallPort.accountId());
        final HashMap<String, EmailPort> created = new HashMap<String, EmailPort>();
        final HashMap<String, SetErrorPort> notCreated = new HashMap<String, SetErrorPort>();

        for (final Map.Entry<String, EmailPort> mailToCreate : setEmailMethodCallPort.getCreate().entrySet()) {

            try {
                email(mailToCreate.getValue(), previousResponses, setEmailMethodCallPort.accountId());
                accountState.increaseEmailState();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                notCreated.put(mailToCreate.getKey(), setErrorEnumPort.invalidProperties());
                continue;
            }
        }

        return new CreatedResult<>(created, notCreated); // FIXME: implement
    }

    private EmailPort email(final EmailPort emailPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses, final String accountId)
            throws InvalidArgumentsException, AccountNotFoundException {
        /*
         * NOTA REMOVE ME
         * boldId != emailId, e allo stesso
         */
        final String emailId = UUID.randomUUID().toString(); // Questo è l'id secondo la specifica JMAP
        final String blobId = UUID.randomUUID().toString(); // Questo è l'id secondo RFC5322 dell'e-mail per i raw email
                                                            // data
        final String threadId = emailPort.getThreadId() != null
                ? CreationIdResolver.resolveIfNecessary(emailPort.getThreadId(), previousResponses)
                : UUID.randomUUID().toString(); // FIXME: funziona veramente così?
        final Long size = 10L; // FIXME: finto
        final Instant recevidAt = Instant.now();
        final Boolean hasAttachment = false; // FIXME: A prescindere, non rientra tra i requisiti?
        final String preview = "";
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
            // TODO: tenere traccia dei cambiamenti
            updateThread(emailPort.getThreadId(), threadId, accountId);
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

    private void updateThread(final String clientThread, final String threadId, final String accountId)
            throws AccountNotFoundException {
        final ThreadChangesTracker threadChangesTracker = threadChangesTrackerRepository.retrive(accountId);
        final AccountState accountState = accountStateRepository.retrive(accountId);
        accountState.increaseThreadState();

        var created = threadChangesTracker.created();
        var updated = threadChangesTracker.updated();

        if (clientThread.equals(threadId)) {
            updated.put(threadId, accountId);
        } else {
            created.put(accountState.threadState(), threadId);
        }

        threadChangesTrackerRepository
                .save(new SimpleThreadChangesTracker(accountId, created, updated, threadChangesTracker.destroyed()));
        accountStateRepository.save(accountState);
    }

    private void updateMailbox(final String[] mailboxIds, final String accountId) {
        // TODO: farlo
    }
}
