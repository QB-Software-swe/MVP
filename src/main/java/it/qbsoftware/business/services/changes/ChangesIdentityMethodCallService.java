package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public class ChangesIdentityMethodCallService implements ChangesIdentityMethodCallUsecase {
    final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;

    public ChangesIdentityMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(ChangesIdentityMethodCallPort changesIdentityMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses) {
        return new MethodResponsePort[] { cannotCalculateChangesMethodErrorResponsePort };
    }
}
