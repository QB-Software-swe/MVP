package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface ChangesMailboxMethodCallUsecase {

    public MethodResponsePort[] call(
            final ChangesMailboxMethodCallPort changesMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousresponses);
}
