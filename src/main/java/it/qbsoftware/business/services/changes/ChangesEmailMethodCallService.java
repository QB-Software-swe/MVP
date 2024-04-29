package it.qbsoftware.business.services.changes;

import java.util.Map;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;

public class ChangesEmailMethodCallService implements ChangesEmailMethodCallUsecase {
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort;
    private final AccountStateRepository accountStateRepository;

    public ChangesEmailMethodCallService(
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort,
            final AccountStateRepository accountStateRepository) {
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.changesEmailMethodResponseBuilderPort = changesEmailMethodResponseBuilderPort;
        this.accountStateRepository = accountStateRepository;
    }

    @Override
    public ChangesEmailMethodResponsePort call(final ChangesEmailMethodCallPort changesEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses)
            throws AccountNotFoundException, InvalidArgumentsException, CannotCalculateChangesException {

        final String accountId = changesEmailMethodCallPort.accountId();
        final Long maxChanges = changesEmailMethodCallPort.getMaxChanges();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        if (maxChanges != null && maxChanges < 0) {
            throw new InvalidArgumentsException();
        }

        if (changesEmailMethodCallPort.getSinceState() != null
                && changesEmailMethodCallPort.getSinceState() == accountState.state()) {
            final String[] empty = new String[] {};

            return changesEmailMethodResponseBuilderPort
                    .reset()
                    .accountId(accountId)
                    .oldState(changesEmailMethodCallPort.getSinceState())
                    .newState(accountState.state())
                    .created(empty)
                    .updated(empty)
                    .destroyed(empty)
                    .hasMoreChanges(false)
                    .build();
        }

        final EmailChangesTracker emailChangesTracker = emailChangesTrackerRepository.retrive(accountId);

        final Map<String, String> created = emailChangesTracker.created();
        final Map<String, String> updated = emailChangesTracker.updated();
        final Map<String, String> destroyed = emailChangesTracker.destroyed();

        if (maxChanges == null || maxChanges == 0 || created.entrySet().stream().count()
                + updated.entrySet().stream().count() + destroyed.entrySet().stream().count() <= maxChanges) {

            return changesEmailMethodResponseBuilderPort
                    .reset()
                    .accountId(accountId)
                    .oldState(changesEmailMethodCallPort.getSinceState())
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
