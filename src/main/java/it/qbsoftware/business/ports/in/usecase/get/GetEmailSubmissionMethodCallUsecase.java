package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface GetEmailSubmissionMethodCallUsecase {
    public MethodResponsePort[] call(final GetEmailSubmissionMethodCallPort getEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}