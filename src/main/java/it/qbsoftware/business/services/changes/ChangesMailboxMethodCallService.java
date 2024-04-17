package it.qbsoftware.business.services.changes;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.error.CannotCalculateChangesMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesMailboxMethodCallUsecase;

public class ChangesMailboxMethodCallService implements ChangesMailboxMethodCallUsecase {
    final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort;

    public ChangesMailboxMethodCallService(
            final CannotCalculateChangesMethodErrorResponsePort cannotCalculateChangesMethodErrorResponsePort) {
        this.cannotCalculateChangesMethodErrorResponsePort = cannotCalculateChangesMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final ChangesMailboxMethodCallPort changesMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses) {

        return new MethodResponsePort[] {
                cannotCalculateChangesMethodErrorResponsePort
        };

    }

}
