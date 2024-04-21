package it.qbsoftware.business.services.set;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.response.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class SetEmailMethodCallService implements SetEmailMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IfInStateMatch ifInStateMatch;
    private final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort;
    private final CreateEmail createEmail;
    private final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;

    public SetEmailMethodCallService(final AccountStateRepository accountStateRepository,
            final IfInStateMatch ifInStateMatch,
            final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort,
            final CreateEmail createEmail, final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort) {
        this.accountStateRepository = accountStateRepository;
        this.ifInStateMatch = ifInStateMatch;
        this.stateMismatchMethodErrorResponsePort = stateMismatchMethodErrorResponsePort;
        this.createEmail = createEmail;
        this.setEmailMethodResponseBuilderPort = setEmailMethodResponseBuilderPort;
    }

    @Override
    public MethodResponsePort[] call(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        try {
            final String accountId = setEmailMethodCallPort.accountId();
            AccountState preSetAccountState = accountStateRepository.retrive(accountId);

            ifInStateMatch.methodStateMatchCurrent(setEmailMethodCallPort.getIfInState(),
                    preSetAccountState.emailState());

            final CreatedResult<EmailPort> createdEmailResult = createEmail.create(setEmailMethodCallPort,
                    previousResponses);

            final UpdatedResult<EmailPort> updatedEmailResult = null;

            final DestroyedResult destroyedEmailResult = null;

            AccountState postSetAccountState = accountStateRepository.retrive(accountId);

            return new MethodResponsePort[] {
                    setEmailMethodResponseBuilderPort
                            .reset()
                            .oldState(preSetAccountState.emailState())
                            .newState(postSetAccountState.emailState())
                            .created(createdEmailResult.created())
                            .notCreated(createdEmailResult.notCreated())
                            .updated(updatedEmailResult.updated())
                            .notUpdated(updatedEmailResult.notUpdated())
                            .destroyed(destroyedEmailResult.destroyed())
                            .notDestroyed(destroyedEmailResult.notDestroyed())
                            .build()
            };

        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
        } catch (final StateMismatchException stateMismatchException) {
            return new MethodResponsePort[] { stateMismatchMethodErrorResponsePort };
        }
    }
}
