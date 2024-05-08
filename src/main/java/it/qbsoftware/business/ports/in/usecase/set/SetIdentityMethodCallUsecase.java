package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponsePort;

public interface SetIdentityMethodCallUsecase {
    public SetIdentityMethodResponsePort call(
            SetIdentityMethodCallPort setIdentityMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponse)
            throws AccountNotFoundException, StateMismatchException;
}
