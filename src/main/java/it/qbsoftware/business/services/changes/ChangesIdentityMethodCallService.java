package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import java.util.Map;

public class ChangesIdentityMethodCallService implements ChangesIdentityMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IdentityChangesTrackerRepository identityChangesTrackerRepository;
    private final ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort;

    public ChangesIdentityMethodCallService(
            final AccountStateRepository accountStateRepository,
            final ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort,
            final IdentityChangesTrackerRepository identityChangesTrackerRepository) {
        ;
        this.accountStateRepository = accountStateRepository;
        this.identityChangesTrackerRepository = identityChangesTrackerRepository;
        this.changesIdentityMethodResponseBuilderPort = changesIdentityMethodResponseBuilderPort;
    }

    @Override
    public ChangesIdentityMethodResponsePort call(
            final ChangesIdentityMethodCallPort changesIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses)
            throws AccountNotFoundException,
                    InvalidArgumentsException,
                    CannotCalculateChangesException {

        final String accountId = changesIdentityMethodCallPort.accountId();
        final Long maxChanges = changesIdentityMethodCallPort.getMaxChanges();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        if (maxChanges != null && maxChanges < 0) {
            throw new InvalidArgumentsException();
        }

        if (changesIdentityMethodCallPort.getSinceState() != null
                && changesIdentityMethodCallPort.getSinceState() == accountState.state()) {
            final String[] empty = new String[] {};

            return changesIdentityMethodResponseBuilderPort
                    .reset()
                    .accountId(accountId)
                    .oldState(changesIdentityMethodCallPort.getSinceState())
                    .newState(accountState.state())
                    .created(empty)
                    .updated(empty)
                    .destroyed(empty)
                    .hasMoreChanges(false)
                    .build();
        }

        final var identityChangesTracker = identityChangesTrackerRepository.retrive(accountId);

        final Map<String, String> created = identityChangesTracker.created();
        final Map<String, String> updated = identityChangesTracker.updated();
        final Map<String, String> destroyed = identityChangesTracker.destroyed();

        if (maxChanges == null
                || maxChanges == 0
                || created.entrySet().stream().count()
                                + updated.entrySet().stream().count()
                                + destroyed.entrySet().stream().count()
                        <= maxChanges) {

            return changesIdentityMethodResponseBuilderPort
                    .reset()
                    .accountId(accountId)
                    .oldState(changesIdentityMethodCallPort.getSinceState())
                    .newState(accountState.state())
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
