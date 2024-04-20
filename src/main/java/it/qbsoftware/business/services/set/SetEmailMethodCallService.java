package it.qbsoftware.business.services.set;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.response.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class SetEmailMethodCallService implements SetEmailMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IfInStateMatch ifInStateMatch;
    private final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort;

    public SetEmailMethodCallService(final AccountStateRepository accountStateRepository,
            final IfInStateMatch ifInStateMatch,
            final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort) {
        this.accountStateRepository = accountStateRepository;
        this.ifInStateMatch = ifInStateMatch;
        this.stateMismatchMethodErrorResponsePort = stateMismatchMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        try {
            final String accountId = setEmailMethodCallPort.accountId();
            AccountState accountState = accountStateRepository.retrive(accountId);

            ifInStateMatch.methodStateMatchCurrent(setEmailMethodCallPort.getIfInState(), accountState.emailState());

            

        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
        } catch (final StateMismatchException stateMismatchException) {
            return new MethodResponsePort[] { stateMismatchMethodErrorResponsePort };
        }

        return new MethodResponsePort[] {}; // FIXME: remove me
    }
}
