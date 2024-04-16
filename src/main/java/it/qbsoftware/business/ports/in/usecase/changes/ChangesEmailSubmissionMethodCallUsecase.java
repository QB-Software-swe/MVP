package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.ports.in.jmap.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface ChangesEmailSubmissionMethodCallUsecase {
    public MethodResponsePort[] call(ChangesEmailSubmissionMethodCallPort changesSubmissionMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
