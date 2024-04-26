package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class StandardDestroyEmail implements DestroyEmail {
    private final EmailRepository emailRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final ThreadChangesTrackerRepository threadChangesTrackerRepository;
    private final SetErrorEnumPort setErrorEnumPort;
    private final AccountStateRepository accountStateRepository;

    public StandardDestroyEmail(final EmailRepository emailRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final SetErrorEnumPort setErrorEnumPort,
            final AccountStateRepository accountStateRepository,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository) {
        this.emailRepository = emailRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.threadChangesTrackerRepository = threadChangesTrackerRepository;
        this.setErrorEnumPort = setErrorEnumPort;
        this.accountStateRepository = accountStateRepository;
    }

    @Override
    public DestroyedResult destroy(final SetEmailMethodCallPort setEmailMethodCallPort)
            throws AccountNotFoundException {
        final String accountId = setEmailMethodCallPort.accountId();
        final String[] idsToDestroy = setEmailMethodCallPort.getDestroy();

        final List<String> destroyed = new ArrayList<String>();
        final Map<String, SetErrorPort> notDestroyed = new HashMap<String, SetErrorPort>();

        for (final String idToDestroy : idsToDestroy) {
            try {
                EmailPort emailDestroyed = emailRepository.destroy(idToDestroy);
                updateEmailChanges(emailDestroyed, accountId);
                updateThreadChanges(emailDestroyed, accountId);
                mailboxUpdateChanges(emailDestroyed, accountId);
                destroyed.add(idToDestroy);
            } catch (final SetNotFoundException SetNotFoundException) {
                notDestroyed.put(idToDestroy, setErrorEnumPort.notFound());
            }
        }

        return new DestroyedResult(destroyed.toArray(String[]::new), notDestroyed);
    }

    private void updateEmailChanges(final EmailPort emailDestroyed, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final EmailChangesTracker emailChangesTracker = emailChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseEmailState();
        emailChangesTracker.emailHasBeenDestroyed(accountState.emailState(), emailDestroyed.getId());

        accountStateRepository.save(accountState);
        emailChangesTrackerRepository.save(emailChangesTracker);
    }

    private void updateThreadChanges(final EmailPort emailDestroyed, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final ThreadChangesTracker threadChangesTracker = threadChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseThreadState();
        threadChangesTracker.threadHasBeenUpdated(accountState.threadState(), emailDestroyed.getThreadId());

        accountStateRepository.save(accountState);
        threadChangesTrackerRepository.save(threadChangesTracker);
    }

    private void mailboxUpdateChanges(final EmailPort emailDestroyed, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        for (final String mailboxId : emailDestroyed.getMailboxIds().keySet()) {
            accountState = accountState.increaseMailboxState();
            mailboxChangesTracker.mailboxHasBeenUpdated(accountState.mailboxState(), mailboxId);
        }

        accountStateRepository.save(accountState);
        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
    }
}
