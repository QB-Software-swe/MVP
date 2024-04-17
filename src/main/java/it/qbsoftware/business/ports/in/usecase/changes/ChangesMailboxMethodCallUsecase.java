package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface ChangesMailboxMethodCallUsecase {

    public MethodResponsePort[] call(
            final ChangesMailboxMethodCallPort changesMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
