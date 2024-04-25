package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;

public interface GetThreadMethodCallUsecase {
    public GetThreadMethodResponsePort call(final GetThreadMethodCallPort getThreadMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previouseResponses) throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException;
}
