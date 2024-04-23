package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetMailboxMethodCallUsecase {

    public MethodResponsePort[] call(SetMailboxMethodCallPort setMailboxMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponse);
}
