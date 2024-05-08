package it.qbsoftware.business.services.set;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyEmail;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class SetEmailMethodCallService implements SetEmailMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IfInStateMatch ifInStateMatch;
    private final CreateEmail createEmail;
    private final UpdateEmail updateEmail;
    private final DestroyEmail destroyEmail;
    private final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;

    public SetEmailMethodCallService(
            final AccountStateRepository accountStateRepository,
            final IfInStateMatch ifInStateMatch,
            final CreateEmail createEmail,
            final UpdateEmail updateEmail,
            final DestroyEmail destroyEmail,
            final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort) {
        this.accountStateRepository = accountStateRepository;
        this.ifInStateMatch = ifInStateMatch;
        this.createEmail = createEmail;
        this.updateEmail = updateEmail;
        this.destroyEmail = destroyEmail;
        this.setEmailMethodResponseBuilderPort = setEmailMethodResponseBuilderPort;
    }

    @Override
    public SetEmailMethodResponsePort call(
            final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException, StateMismatchException {

        final String accountId = setEmailMethodCallPort.accountId();
        final AccountState preSetAccountState = accountStateRepository.retrive(accountId);

        ifInStateMatch.methodStateMatchCurrent(
                setEmailMethodCallPort.ifInState(), preSetAccountState.state());

        final CreatedResult<EmailPort> createdEmailResult =
                createEmail.create(setEmailMethodCallPort, previousResponses);

        final UpdatedResult<EmailPort> updatedEmailResult =
                updateEmail.update(setEmailMethodCallPort, previousResponses);

        final DestroyedResult destroyedEmailResult = destroyEmail.destroy(setEmailMethodCallPort);

        final AccountState postSetAccountState = accountStateRepository.retrive(accountId);

        return setEmailMethodResponseBuilderPort
                .reset()
                .oldState(preSetAccountState.state())
                .newState(postSetAccountState.state())
                .created(createdEmailResult.created())
                .notCreated(createdEmailResult.notCreated())
                .updated(updatedEmailResult.updated())
                .notUpdated(updatedEmailResult.notUpdated())
                .destroyed(destroyedEmailResult.destroyed())
                .notDestroyed(destroyedEmailResult.notDestroyed())
                .build();
    }
}
