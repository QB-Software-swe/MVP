package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesThreadMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public class ChangesThreadMethodCallService implements ChangesThreadMethodCallUsecase {

    final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;

    public ChangesThreadMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(ChangesThreadMethodCallPort changesThreadMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses) {
        return new MethodResponsePort[] {
                cannotCalculateChangesMethodErrorResponsePort
        };
    }

}
