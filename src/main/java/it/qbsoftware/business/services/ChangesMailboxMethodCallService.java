package it.qbsoftware.business.services;

import java.util.Optional;

import it.qbsoftware.business.domain.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.Update;
import it.qbsoftware.business.ports.in.jmap.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.ChangesMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;

//TODO: implementare la logica
@SuppressWarnings("unused")
public class ChangesMailboxMethodCallService implements ChangesMailboxMethodCallUsecase {
    final AccountStateRepository accountStateRepository;
    final ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort;
    final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;

    // FIXME: il costruttore è stato messo in modo deliberato come privato,
    // implementare completamente la classe e poi metterlo come non pubblico
    public ChangesMailboxMethodCallService(final AccountStateRepository accountStateRepository,
            final ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort,
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort) {
        this.accountStateRepository = accountStateRepository;
        this.changesMailboxMethodResponseBuilderPort = changesMailboxMethodResponseBuilderPort;
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final ChangesMailboxMethodCallPort changesMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses) {

        /*
         * final String accountId = changesMailboxMethodCallPort.accountId();
         * Optional<AccountState> accountState =
         * accountStateRepository.retrive(accountId);
         * if (!accountState.isPresent()) {
         * return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
         * }
         * 
         * final String clientState = changesMailboxMethodCallPort.getSinceState();
         * final Long maxChanges = changesMailboxMethodCallPort.getMaxChanges(); //
         * TODO: controllare che i maxChanges
         * 
         * if (clientState != null &&
         * clientState.equals(accountState.get().mailboxState())) {
         * return new MethodResponsePort[] {
         * changesMailboxMethodResponseBuilderPort
         * .reset()
         * .oldState(clientState)
         * .newState(accountState.get().mailboxState())
         * .updated(new String[0])
         * .created(new String[0])
         * .destroyed(new String[0])
         * .updatedProperties(new String[0])
         * .build()
         * };
         * } else {
         * final Update update = null;
         * }
         */

        return new MethodResponsePort[] {
                cannotCalculateChangesMethodErrorResponsePort
        }; // FIXME: non riesce a calcolare i change a prescindere

    }

}
