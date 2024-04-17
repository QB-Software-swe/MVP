package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface GetMailboxMethodCallUsecase {
    public MethodResponsePort[] call(final GetMailboxMethodCallPort getMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
