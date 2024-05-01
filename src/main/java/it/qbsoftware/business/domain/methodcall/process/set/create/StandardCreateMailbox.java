package it.qbsoftware.business.domain.methodcall.process.set.create;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class StandardCreateMailbox implements CreateMailbox {
    private final AccountStateRepository accountStateRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final MailboxRepository mailboxRepository;
    private final SetErrorEnumPort setErrorEnumPort;

    public StandardCreateMailbox(final AccountStateRepository accountStateRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final MailboxRepository mailboxRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        this.accountStateRepository = accountStateRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.mailboxRepository = mailboxRepository;
        this.setErrorEnumPort = setErrorEnumPort;
    }

    @Override
    public CreatedResult<MailboxPort> create(final SetMailboxMethodCallPort setMailboxMethodCallPort)
            throws AccountNotFoundException {
        final HashMap<String, MailboxPort> created = new HashMap<>();
        final HashMap<String, SetErrorPort> notCreated = new HashMap<>();

        final var x = setMailboxMethodCallPort.getCreate();
        if (x != null) {
            for (final Map.Entry<String, MailboxPort> mailboxEntry : x.entrySet()) {
                try {
                    MailboxPort mailboxDiff = createMailbox(mailboxEntry.getValue(),
                            setMailboxMethodCallPort.accountId());
                    created.put(mailboxEntry.getKey(), mailboxDiff);
                } catch (final SetSingletonException setSingletonException) {
                    notCreated.put(mailboxEntry.getKey(), setErrorEnumPort.singleton());
                } catch (InvalidArgumentsException e) {
                    notCreated.put(mailboxEntry.getKey(), setErrorEnumPort.invalidProperties());
                }
            }
        }

        return new CreatedResult<>(created, notCreated);
    }

    private MailboxPort createMailbox(final MailboxPort mailbox, final String accountId)
            throws AccountNotFoundException, SetSingletonException, InvalidArgumentsException {
        final String mailboxId = accountId + "/" + UUID.randomUUID().toString();

        if(mailbox.getName()=="") {
            throw new InvalidArgumentsException();
        }

        final MailboxPort mailboxToSave = mailbox.getBuilder().id(mailboxId).build();
        final MailboxPort mailboxDiff = mailbox.getBuilder().reset().id(mailboxId).build();
        mailboxRepository.save(mailboxToSave);
        mailboxUpdateChanges(mailboxId, accountId);

        return mailboxDiff;
    }

    private void mailboxUpdateChanges(final String mailboxId, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseState();
        mailboxChangesTracker.mailboxHasBeenCreated(accountState.state(), mailboxId);

        accountStateRepository.save(accountState);
        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
    }
}
