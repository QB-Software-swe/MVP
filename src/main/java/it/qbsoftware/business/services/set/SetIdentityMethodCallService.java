package it.qbsoftware.business.services.set;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class SetIdentityMethodCallService implements SetIdentityMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IfInStateMatch ifInStateMatch;
    private final CreateIdentity createIndetity;
    private final SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort;

    public SetIdentityMethodCallService(
            final AccountStateRepository accountStateRepository,
            final CreateIdentity createIndetity,
            final IfInStateMatch ifInStateMatch,
            final SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort) {
        this.accountStateRepository = accountStateRepository;
        this.ifInStateMatch = ifInStateMatch;
        this.createIndetity = createIndetity;
        this.setIdentityMethodResponseBuilderPort = setIdentityMethodResponseBuilderPort;
    }

    @Override
    public SetIdentityMethodResponsePort call(
            final SetIdentityMethodCallPort setIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse)
            throws AccountNotFoundException, StateMismatchException {

        final String accountId = setIdentityMethodCallPort.accountId();
        final AccountState preSetAccountState = accountStateRepository.retrive(accountId);

        ifInStateMatch.methodStateMatchCurrent(
                setIdentityMethodCallPort.ifInState(), preSetAccountState.state());

        final CreatedResult<IdentityPort> createdIdentityResult =
                createIndetity.create(setIdentityMethodCallPort);

        final AccountState postSetAccountState = accountStateRepository.retrive(accountId);

        return setIdentityMethodResponseBuilderPort
                .reset()
                .oldState(preSetAccountState.state())
                .newState(postSetAccountState.state())
                .created(createdIdentityResult.created())
                .notCreated(createdIdentityResult.notCreated())
                .build();
    }
}
