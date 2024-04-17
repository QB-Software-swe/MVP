package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;

public class ChangesEmailSubmissionMethodCallService implements ChangesEmailSubmissionMethodCallUsecase {
    final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;

    public ChangesEmailSubmissionMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(ChangesEmailSubmissionMethodCallPort changesSubmissionMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses) {
        return new MethodResponsePort[] { cannotCalculateChangesMethodErrorResponsePort };
    }

}
