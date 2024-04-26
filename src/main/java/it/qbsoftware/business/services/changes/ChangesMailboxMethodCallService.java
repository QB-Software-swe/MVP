package it.qbsoftware.business.services.changes;

import java.util.Map;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;

public class ChangesMailboxMethodCallService implements ChangesMailboxMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort;

    public ChangesMailboxMethodCallService(
            final AccountStateRepository accountStateRepository,
            final ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository) {
        this.accountStateRepository = accountStateRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.changesMailboxMethodResponseBuilderPort = changesMailboxMethodResponseBuilderPort;
    }

    @Override
    public ChangesMailboxMethodResponsePort call(final ChangesMailboxMethodCallPort changesMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses)
            throws InvalidArgumentsException, AccountNotFoundException, CannotCalculateChangesException {

        final String accountId = changesMailboxMethodCallPort.accountId();
        final Long maxChanges = changesMailboxMethodCallPort.getMaxChanges();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        if (maxChanges != null && maxChanges <= 0) {
            throw new InvalidArgumentsException();
        }

        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        final Map<String, String> created = mailboxChangesTracker.created();
        final Map<String, String> updated = mailboxChangesTracker.updated();
        final Map<String, String> destroyed = mailboxChangesTracker.destroyed();

        if (maxChanges == null || maxChanges == 0 || created.entrySet().stream().count()
                + updated.entrySet().stream().count() + destroyed.entrySet().stream().count() <= maxChanges) {

            return changesMailboxMethodResponseBuilderPort
                    .reset()
                    .accountId(accountId)
                    .oldState(changesMailboxMethodCallPort.getSinceState())
                    .newState(accountState.emailState())
                    .created(created.values().stream().toArray(String[]::new))
                    .updated(updated.values().stream().toArray(String[]::new))
                    .destroyed(destroyed.values().stream().toArray(String[]::new))
                    .hasMoreChanges(false)
                    .build();
        } else {
            throw new CannotCalculateChangesException();
        }
    }

}
