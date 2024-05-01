package it.qbsoftware.business.domain.methodcall.process.set.create;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;

public class StandardCreateEmail implements CreateEmail {
    private final EmailRepository emailRepository;
    private final EmailBuilderPort emailBuilderPort;
    private final ThreadRepository threadRepository;
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final ThreadChangesTrackerRepository threadChangesTrackerRepository;
    private final AccountStateRepository accountStateRepository;
    private final SetErrorEnumPort setErrorEnumPort;
    private final CreationIdResolverPort creationIdResolverPort;

    public StandardCreateEmail(final EmailBuilderPort emailBuilderPort,
            final EmailRepository emailRepository,
            final AccountStateRepository accountStateRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository,
            final SetErrorEnumPort setErrorEnumPort, final ThreadRepository threadRepository,
            final CreationIdResolverPort creationIdResolverPort) {
        this.emailRepository = emailRepository;
        this.emailBuilderPort = emailBuilderPort;
        this.threadRepository = threadRepository;
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.threadChangesTrackerRepository = threadChangesTrackerRepository;
        this.accountStateRepository = accountStateRepository;
        this.setErrorEnumPort = setErrorEnumPort;
        this.creationIdResolverPort = creationIdResolverPort;
    }

    @Override
    public CreatedResult<EmailPort> create(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException {
        final Map<String, EmailPort> mapEmailToCreate = setEmailMethodCallPort.getCreate();

        final HashMap<String, EmailPort> created = new HashMap<String, EmailPort>();
        final HashMap<String, SetErrorPort> notCreated = new HashMap<String, SetErrorPort>();

        if (mapEmailToCreate != null) {
            for (final Map.Entry<String, EmailPort> mailToCreate : mapEmailToCreate.entrySet()) {

                try {
                    final EmailPort emailDiff = email(mailToCreate.getValue(), previousResponses,
                            setEmailMethodCallPort.accountId());
                    created.put(mailToCreate.getKey(), emailDiff);
                } catch (final InvalidArgumentsException invalidArgumentsException) {
                    notCreated.put(mailToCreate.getKey(), setErrorEnumPort.invalidProperties());
                    continue;
                }
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
        final Optional<String> resolvedThread = resolveThread(emailPort.getSubject(), accountId);
        final String threadId = resolvedThread.isPresent() ? resolvedThread.get()
                : accountId + "/" + UUID.randomUUID().toString();
        ;

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
                .hasAttachment(false)
                .clearMailboxIds()
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

        if (emailPort.getBodyStructure() == null) {
            final List<EmailBodyPartPort> x = emailPort.getTextBody();
            emailToSaveBuilder.bodyStructure(x.get(0));
        }

        try {
            emailRepository.save(emailToSaveBuilder.build());
            accountStateRepository.save(accountStateRepository.retrive(accountId).increaseState());

            ThreadPort x = threadRepository.retriveOne(threadId);
            LinkedHashSet<String> setEmailBelongThisThread = new LinkedHashSet<>(x.getEmailIds());
            if (!setEmailBelongThisThread.contains(emailId)) {
                setEmailBelongThisThread.add(emailId);
                threadRepository.save(x.toBuilder().clearEmailIds().emailIds(setEmailBelongThisThread).build());
            }

            updateEmailChanges(emailId, accountId);

            if (resolvedThread.isPresent()) {
                updateThreadChanges(threadId, accountId);
            } else {
                createThreadChanges(threadId, accountId);
            }

            updateMailbox(mailboxIds.keySet().toArray(String[]::new), accountId);
        } catch (final SetSingletonException setSingletonException) {
            return email(emailPort, previousResponses, accountId);
        }

        return emailWithOnlyServerSetPropertiesBuilder.build();
    }

    private EmailBodyPartPort injectId(final EmailBodyPartPort attachment) {
        return attachment
                .toBuilder()
                .reset()
                .blobId(UUID.randomUUID().toString())
                .charset(attachment.getCharset())
                .name(attachment.getName())
                .size(attachment.getSize())
                .build();
    }

    private Map<String, Boolean> resolveMailboxIdsReference(final Map<String, Boolean> mailboxIds,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) throws InvalidArgumentsException {
        if (mailboxIds == null) {
            throw new InvalidArgumentsException();
        }

        final Map<String, Boolean> resolvedIdReference = new HashMap<>();
        for (final Map.Entry<String, Boolean> mailbox : mailboxIds.entrySet()) {
            resolvedIdReference.put(creationIdResolverPort.resolveIfNecessary(mailbox.getKey(), previousResponses),
                    mailbox.getValue());
        }

        return resolvedIdReference;
    }

    private void updateEmailChanges(final String emailId, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final EmailChangesTracker emailChangesTracker = emailChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseState();
        emailChangesTracker.emailHasBeenCreated(accountState.state(), emailId);

        accountStateRepository.save(accountState);
        emailChangesTrackerRepository.save(emailChangesTracker);
    }

    private void updateThreadChanges(final String threadId, final String accountId)
            throws AccountNotFoundException {
        ThreadChangesTracker threadChangesTracker = threadChangesTrackerRepository.retrive(accountId);
        AccountState accountState = accountStateRepository.retrive(accountId);
        accountState = accountState.increaseState();

        threadChangesTracker = threadChangesTracker.threadHasBeenUpdated(accountState.state(), threadId);

        threadChangesTrackerRepository.save(threadChangesTracker);
        accountStateRepository.save(accountState);
    }

    private void createThreadChanges(final String threadId, final String accountId)
            throws AccountNotFoundException {
        ThreadChangesTracker threadChangesTracker = threadChangesTrackerRepository.retrive(accountId);
        AccountState accountState = accountStateRepository.retrive(accountId);
        accountState = accountState.increaseState();

        threadChangesTracker = threadChangesTracker.threadHasBeenCreated(accountState.state(), threadId);

        threadChangesTrackerRepository.save(threadChangesTracker);
        accountStateRepository.save(accountState);
    }

    private void updateMailbox(final String[] mailboxIds, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        // NOTA: mailboxIds == #di?
        for (final String id : mailboxIds) {
            accountState = accountState.increaseState();
            mailboxChangesTracker.mailboxHasBeenUpdated(accountState.state(), id);
        }

        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
        accountStateRepository.save(accountState);
    }

    private Optional<String> resolveThread(final String subject, final String accountId) {
        if (subject.substring(0, 3).equals("Re:")) {
            final var x = Arrays.asList(emailRepository.retriveAll(accountId).found()).stream()
                    .filter(e -> e.getSubject().trim().equals(subject.substring(3).trim())).findAny();
            if (x.isPresent()) {
                return Optional.of(x.get().getThreadId());
            }
        }

        return Optional.empty();
    }
}
