package it.qbsoftware.business.services.set;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.set.SetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class SetIdentityMethodCallService implements SetIdentityMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IfInStateMatch ifInStateMatch;
    private final CreateIdentity createIndetity;
    private final UpdateIdentity updateIdentity;
    private final DestroyIdentity destroyIdentity;
    private final SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort;
    private final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort;
    private final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    public SetIdentityMethodCallService(final AccountStateRepository accountStateRepository,
            final CreateIdentity createIndetity,
            final DestroyIdentity destroyIdentity,
            final IfInStateMatch ifInStateMatch,
            final SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort,
            final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort,
            final UpdateIdentity updateIdentity,
            final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort) {
        this.accountStateRepository = accountStateRepository;
        this.ifInStateMatch = ifInStateMatch;
        this.createIndetity = createIndetity;
        this.updateIdentity = updateIdentity;
        this.destroyIdentity = destroyIdentity;
        this.setIdentityMethodResponseBuilderPort = setIdentityMethodResponseBuilderPort;
        this.stateMismatchMethodErrorResponsePort = stateMismatchMethodErrorResponsePort;
        this.accountNotFoundMethodErrorResponsePort = accountNotFoundMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final SetIdentityMethodCallPort setIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse) {
        try {
            final String accountId = setIdentityMethodCallPort.accountId();
            AccountState preSetAccountState = accountStateRepository.retrive(accountId);

            ifInStateMatch.methodStateMatchCurrent(setIdentityMethodCallPort.ifInState(),
                    preSetAccountState.emailState());

            final CreatedResult<IdentityPort> createdIdentityResult = createIndetity.create(setIdentityMethodCallPort);

            final UpdatedResult<IdentityPort> updatedIdentityResult = updateIdentity.update(setIdentityMethodCallPort);

            final DestroyedResult destroyedIdentityResult = destroyIdentity.destroy(setIdentityMethodCallPort);

            AccountState postSetAccountState = accountStateRepository.retrive(accountId);

            return new MethodResponsePort[] {
                    setIdentityMethodResponseBuilderPort
                            .reset()
                            .oldState(preSetAccountState.emailState())
                            .newState(postSetAccountState.emailState())
                            .created(createdIdentityResult.created())
                            .notCreated(createdIdentityResult.notCreated())
                            .updated(updatedIdentityResult.updated())
                            .notUpdated(updatedIdentityResult.notUpdated())
                            .destroyed(destroyedIdentityResult.destroyed())
                            .notDestroyed(destroyedIdentityResult.notDestroyed())
                            .build()
            };

        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { accountNotFoundMethodErrorResponsePort };
        } catch (final StateMismatchException stateMismatchException) {
            return new MethodResponsePort[] { stateMismatchMethodErrorResponsePort };
        }
    }

}
