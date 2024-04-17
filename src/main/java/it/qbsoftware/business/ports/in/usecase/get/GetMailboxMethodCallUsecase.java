package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface GetMailboxMethodCallUsecase {
    public MethodResponsePort[] call(final GetMailboxMethodCallPort getMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
