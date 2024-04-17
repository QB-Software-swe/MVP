package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetEmailMethodCallUsecase {

    public MethodResponsePort[] call(
            final it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
