package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.ports.in.jmap.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public class ChangesEmailMethodCallService implements ChangesEmailMethodCallUsecase {

    final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;

    public ChangesEmailMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(ChangesEmailMethodCallPort changesEmailMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses) {
                return new MethodResponsePort[] {cannotCalculateChangesMethodErrorResponsePort};
    }

}
