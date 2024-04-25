package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailSubmissionChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailSubmissionChangesTrackerRepository;

import java.util.Map;

public class ChangesEmailSubmissionMethodCallService implements ChangesEmailSubmissionMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final EmailSubmissionChangesTrackerRepository emailSubmissionChangesTrackerRepository;
    private final ChangesEmailSubmissionMethodResponseBuilderPort changesEmailSubmissionMethodResponseBuilderPort;

    public ChangesEmailSubmissionMethodCallService(
            final AccountStateRepository accountStateRepository,
            final EmailSubmissionChangesTrackerRepository emailSubmissionChangesTrackerRepository,
            final ChangesEmailSubmissionMethodResponseBuilderPort changesEmailSubmissionMethodResponseBuilderPort) {
        this.accountStateRepository = accountStateRepository;
        this.emailSubmissionChangesTrackerRepository = emailSubmissionChangesTrackerRepository;
        this.changesEmailSubmissionMethodResponseBuilderPort = changesEmailSubmissionMethodResponseBuilderPort;
    }

    @Override
    public ChangesEmailSubmissionMethodResponsePort call(
            final ChangesEmailSubmissionMethodCallPort changesSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses)
            throws AccountNotFoundException, InvalidArgumentsException, CannotCalculateChangesException {

        final String accountId = changesSubmissionMethodCallPort.accountId();
        final Long maxChanges = changesSubmissionMethodCallPort.getMaxChanges();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        if (maxChanges != null && maxChanges <= 0) {
            throw new InvalidArgumentsException();
        }

        final EmailSubmissionChangesTracker emailChangesTracker = emailSubmissionChangesTrackerRepository
                .retrive(accountId);

        final Map<String, String> created = emailChangesTracker.created();
        final Map<String, String> updated = emailChangesTracker.updated();
        final Map<String, String> destroyed = emailChangesTracker.destroyed();

        if (maxChanges == null || maxChanges == 0 || created.entrySet().stream().count()
                + updated.entrySet().stream().count() + destroyed.entrySet().stream().count() <= maxChanges) {

            return changesEmailSubmissionMethodResponseBuilderPort
                    .reset()
                    .accountId(accountId)
                    .oldState(changesSubmissionMethodCallPort.getSinceState())
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
