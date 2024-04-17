package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface GetIdentityMethodCallUsecase {

    public MethodResponsePort[] call(final GetIdentityMethodCallPort getIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);

}
