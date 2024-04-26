package it.qbsoftware.business.services.set;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class SetMailboxMethodCallService implements SetMailboxMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final IfInStateMatch ifInStateMatch;
    private final SetMailboxMethodResponseBuilderPort setMailboxMethodResponseBuilderPort;
    private final CreateMailbox createMailbox;
    private final UpdateMailbox updateMailbox;
    private final DestroyMailbox destroyMailbox;

    public SetMailboxMethodCallService(final AccountStateRepository accountStateRepository,
            final IfInStateMatch ifInStateMatch,
            final SetMailboxMethodResponseBuilderPort setMailboxMethodResponseBuilderPort,
            final CreateMailbox createMailbox,
            final DestroyMailbox destroyMailbox,
            final UpdateMailbox updateMailbox) {
        this.accountStateRepository = accountStateRepository;
        this.ifInStateMatch = ifInStateMatch;
        this.setMailboxMethodResponseBuilderPort = setMailboxMethodResponseBuilderPort;
        this.createMailbox = createMailbox;
        this.updateMailbox = updateMailbox;
        this.destroyMailbox = destroyMailbox;
    }

    @Override
    public SetMailboxMethodResponsePort call(final SetMailboxMethodCallPort setMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse)
            throws StateMismatchException, AccountNotFoundException {

        final String accountId = setMailboxMethodCallPort.accountId();
        final AccountState preSetAccountState = accountStateRepository.retrive(accountId);

        ifInStateMatch.methodStateMatchCurrent(setMailboxMethodCallPort.ifInState(),
                preSetAccountState.mailboxState());

        final CreatedResult<MailboxPort> createdMailboxResult = createMailbox.create(setMailboxMethodCallPort);
        final UpdatedResult<MailboxPort> updatedMailboxResult = updateMailbox.update(setMailboxMethodCallPort);
        final DestroyedResult destroyedMailboxResult = destroyMailbox.destroy(setMailboxMethodCallPort);

        final AccountState postSetAccountState = accountStateRepository.retrive(accountId);

        return setMailboxMethodResponseBuilderPort
                .reset()
                .oldState(preSetAccountState.emailState())
                .newState(postSetAccountState.emailState())
                .created(createdMailboxResult.created())
                .notCreated(createdMailboxResult.notCreated())
                .updated(updatedMailboxResult.updated())
                .notUpdated(updatedMailboxResult.notUpdated())
                .destroyed(destroyedMailboxResult.destroyed())
                .notDestroyed(destroyedMailboxResult.notDestroyed())
                .build();
    }

}
