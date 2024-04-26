package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;

public interface SetEmailMethodCallUsecase {

    public SetEmailMethodResponsePort call(
            final it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) throws AccountNotFoundException, StateMismatchException;
}
