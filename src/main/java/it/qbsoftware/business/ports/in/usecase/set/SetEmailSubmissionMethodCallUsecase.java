package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetEmailSubmissionMethodCallUsecase {
    public MethodResponsePort[] call(final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse);
}
