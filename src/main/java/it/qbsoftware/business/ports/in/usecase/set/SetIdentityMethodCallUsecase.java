package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetIdentityMethodCallUsecase {
    public MethodResponsePort[] call(SetIdentityMethodCallPort setIdentityMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponse);
}
