package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.jmap.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface ChangesEmailMethodCallUsecase {
    public MethodResponsePort[] call(ChangesEmailMethodCallPort changesEmailMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
