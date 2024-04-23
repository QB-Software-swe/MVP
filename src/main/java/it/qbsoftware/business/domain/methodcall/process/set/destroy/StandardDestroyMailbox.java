package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class StandardDestroyMailbox implements DestroyMailbox {
    private final AccountStateRepository accountStateRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final MailboxRepository mailboxRepository;
    private final SetErrorEnumPort setErrorEnumPort;

    public StandardDestroyMailbox(final AccountStateRepository accountStateRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final MailboxRepository mailboxRepository, final SetErrorEnumPort setErrorEnumPort) {
        this.accountStateRepository = accountStateRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.mailboxRepository = mailboxRepository;
        this.setErrorEnumPort = setErrorEnumPort;

    }

    @Override
    public DestroyedResult destroy(final SetMailboxMethodCallPort setMailboxMethodCallPort)
            throws AccountNotFoundException {
        final List<String> destroyed = new ArrayList<>();
        final HashMap<String, SetErrorPort> notDestroyed = new HashMap<>();

        for (final String idMailbox : setMailboxMethodCallPort.getDestroy()) {
            try {
                destroyMailbox(idMailbox, setMailboxMethodCallPort.accountId());
                destroyed.add(idMailbox);
            } catch (final SetNotFoundException setNotFoundException) {
                notDestroyed.put(idMailbox, setErrorEnumPort.notFound());
            }
        }

        return new DestroyedResult(destroyed.toArray(String[]::new), notDestroyed);
    }

    private void destroyMailbox(final String idMailbox, final String accountId)
            throws AccountNotFoundException, SetNotFoundException {
        mailboxRepository.destroy(idMailbox);
        updateMailboxChanges(idMailbox, accountId);
    }

    private void updateMailboxChanges(final String idMailbox, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseMailboxState();
        mailboxChangesTracker.mailboxHasBeenCreated(accountState.mailboxState(), idMailbox);

        accountStateRepository.save(accountState);
        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
    }
}
