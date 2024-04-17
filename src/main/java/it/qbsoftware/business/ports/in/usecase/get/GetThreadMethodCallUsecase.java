package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface GetThreadMethodCallUsecase {
    public MethodResponsePort[] call(final GetThreadMethodCallPort getThreadMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previouseResponses);
}
