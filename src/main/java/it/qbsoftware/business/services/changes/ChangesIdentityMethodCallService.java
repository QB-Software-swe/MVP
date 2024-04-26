package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import java.util.Map;

public class ChangesIdentityMethodCallService implements ChangesIdentityMethodCallUsecase {
    private final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;
    private final AccountStateRepository accountStateRepository;
    private final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
    private final IdentityChangesTrackerRepository identityChangesTrackerRepository;
    private final ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort;
    private final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    public ChangesIdentityMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort,
            final AccountStateRepository accountStateRepository,
            final ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort,
            final IdentityChangesTrackerRepository identityChangesTrackerRepository,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
            final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
        this.accountStateRepository = accountStateRepository;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
        this.identityChangesTrackerRepository = identityChangesTrackerRepository;
        this.changesIdentityMethodResponseBuilderPort = changesIdentityMethodResponseBuilderPort;
        this.accountNotFoundMethodErrorResponsePort = accountNotFoundMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final ChangesIdentityMethodCallPort changesIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses) {
        try {
            final String accountId = changesIdentityMethodCallPort.accountId();
            final Long maxChanges = changesIdentityMethodCallPort.getMaxChanges();
            final AccountState accountState = accountStateRepository.retrive(accountId);

            if (maxChanges != null && maxChanges <= 0) {
                return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
            }

            final var identityChangesTracker = identityChangesTrackerRepository.retrive(accountId);

            final Map<String, String> created = identityChangesTracker.created();
            final Map<String, String> updated = identityChangesTracker.updated();
            final Map<String, String> destroyed = identityChangesTracker.destroyed();

            if (maxChanges == null || maxChanges == 0 || created.entrySet().stream().count()
                    + updated.entrySet().stream().count() + destroyed.entrySet().stream().count() <= maxChanges) {

                return new MethodResponsePort[] {
                        changesIdentityMethodResponseBuilderPort
                                .reset()
                                .accountId(accountId)
                                .oldState(changesIdentityMethodCallPort.getSinceState())
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
