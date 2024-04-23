package it.qbsoftware.business.services.changes;

import java.util.Map;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesThreadMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;

public class ChangesThreadMethodCallService implements ChangesThreadMethodCallUsecase {
    private final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;
    private final AccountStateRepository accountStateRepository;
    private final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
    private final ThreadChangesTrackerRepository threadChangesTrackerRepository;
    private final ChangesThreadMethodResponseBuilderPort changesThreadMethodResponseBuilderPort;
    private final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    public ChangesThreadMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort,
            final AccountStateRepository accountStateRepository,
            final ChangesThreadMethodResponseBuilderPort changesThreadMethodResponseBuilderPort,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository,
            final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
        this.accountStateRepository = accountStateRepository;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
        this.threadChangesTrackerRepository = threadChangesTrackerRepository;
        this.changesThreadMethodResponseBuilderPort = changesThreadMethodResponseBuilderPort;
        this.accountNotFoundMethodErrorResponsePort = accountNotFoundMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final ChangesThreadMethodCallPort changesThreadMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses) {
        try {
            final String accountId = changesThreadMethodCallPort.accountId();
            final Long maxChanges = changesThreadMethodCallPort.getMaxChanges();
            final AccountState accountState = accountStateRepository.retrive(accountId);

            if (maxChanges != null && maxChanges <= 0) {
                return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
            }

            final ThreadChangesTracker threadChangesTracker = threadChangesTrackerRepository.retrive(accountId);

            final Map<String, String> created = threadChangesTracker.created();
            final Map<String, String> updated = threadChangesTracker.updated();
            final Map<String, String> destroyed = threadChangesTracker.destroyed();

            if (maxChanges == null || maxChanges == 0 || created.entrySet().stream().count()
                    + updated.entrySet().stream().count() + destroyed.entrySet().stream().count() <= maxChanges) {

                return new MethodResponsePort[] {
                        changesThreadMethodResponseBuilderPort
                                .reset()
                                .accountId(accountId)
                                .oldState(changesThreadMethodCallPort.getSinceState())
                                .newState(accountState.emailState())
                                .created(created.values().stream().toArray(String[]::new))
                                .updated(updated.values().stream().toArray(String[]::new))
                                .destroyed(destroyed.values().stream().toArray(String[]::new))
                                .hasMoreChanges(false)
                                .build()
                };
            } else {

                return new MethodResponsePort[] { cannotCalculateChangesMethodErrorResponsePort }; // TODO: è possibile
                                                                                                   // fermarsi a stati
                                                                                                   // intermedi?
            }
        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { accountNotFoundMethodErrorResponsePort };
        }
    }

}
