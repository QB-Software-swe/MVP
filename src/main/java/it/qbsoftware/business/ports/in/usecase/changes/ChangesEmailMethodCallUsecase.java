package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface ChangesEmailMethodCallUsecase {
    public MethodResponsePort[] call(ChangesEmailMethodCallPort changesEmailMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
