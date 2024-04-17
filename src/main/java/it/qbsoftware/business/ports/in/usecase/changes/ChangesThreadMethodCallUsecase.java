package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface ChangesThreadMethodCallUsecase {
    public MethodResponsePort[] call(ChangesThreadMethodCallPort changesThreadMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
