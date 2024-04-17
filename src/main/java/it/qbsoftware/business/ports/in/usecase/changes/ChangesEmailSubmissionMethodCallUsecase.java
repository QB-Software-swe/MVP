package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface ChangesEmailSubmissionMethodCallUsecase {
    public MethodResponsePort[] call(ChangesEmailSubmissionMethodCallPort changesSubmissionMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
