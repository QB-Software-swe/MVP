package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.ports.in.jmap.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

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
