package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface ChangesIdentityMethodCallUsecase {
    public MethodResponsePort[] call(ChangesIdentityMethodCallPort changesMailboxMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
