package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;

public interface SetMailboxMethodCallUsecase {

    public SetMailboxMethodResponsePort call(SetMailboxMethodCallPort setMailboxMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponse) throws StateMismatchException, AccountNotFoundException;
}
