package it.qbsoftware.business.services.changes;

import java.util.Map;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;

public class ChangesEmailMethodCallService implements ChangesEmailMethodCallUsecase {
    private final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;
    private final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort;
    private final AccountStateRepository accountStateRepository;
    private final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    public ChangesEmailMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort,
            final AccountStateRepository accountStateRepository,
            final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.changesEmailMethodResponseBuilderPort = changesEmailMethodResponseBuilderPort;
        this.accountStateRepository = accountStateRepository;
        this.accountNotFoundMethodErrorResponsePort = accountNotFoundMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final ChangesEmailMethodCallPort changesEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses) {

        try {
            final String accountId = changesEmailMethodCallPort.accountId();
            final Long maxChanges = changesEmailMethodCallPort.getMaxChanges();
            final AccountState accountState = accountStateRepository.retrive(accountId);

            if (maxChanges != null && maxChanges <= 0) {
                return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
            }

            final EmailChangesTracker emailChangesTracker = emailChangesTrackerRepository.retrive(accountId);

            final Map<String, String> created = emailChangesTracker.created();
            final Map<String, String> updated = emailChangesTracker.updated();
            final Map<String, String> destroyed = emailChangesTracker.destroyed();

            if (maxChanges == null || maxChanges == 0 || created.entrySet().stream().count()
                    + updated.entrySet().stream().count() + destroyed.entrySet().stream().count() <= maxChanges) {

                return new MethodResponsePort[] {
                        changesEmailMethodResponseBuilderPort
                                .reset()
                                .accountId(accountId)
                                .oldState(changesEmailMethodCallPort.getSinceState())
                                .newState(accountState.emailState())
                                .created(created.values().stream().toArray(String[]::new))
                                .updated(updated.values().stream().toArray(String[]::new))
                                .destroyed(destroyed.values().stream().toArray(String[]::new))
                                .hasMoreChanges(false)
                                .build()
                };
            } else {

                return new MethodResponsePort[] { cannotCalculateChangesMethodErrorResponsePort };
            }
        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { accountNotFoundMethodErrorResponsePort };
        }
    }
}
