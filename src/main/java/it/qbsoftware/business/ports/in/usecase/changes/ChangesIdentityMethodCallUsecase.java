package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface ChangesIdentityMethodCallUsecase {
    public MethodResponsePort[] call(ChangesIdentityMethodCallPort changesMailboxMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
