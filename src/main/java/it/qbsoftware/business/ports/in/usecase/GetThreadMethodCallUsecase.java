package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface GetThreadMethodCallUsecase {
    public MethodResponsePort[] call(final GetThreadMethodCallPort getThreadMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previouseResponses);
}
