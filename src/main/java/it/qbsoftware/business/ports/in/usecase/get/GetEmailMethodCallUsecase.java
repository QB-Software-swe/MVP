package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface GetEmailMethodCallUsecase {

    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
