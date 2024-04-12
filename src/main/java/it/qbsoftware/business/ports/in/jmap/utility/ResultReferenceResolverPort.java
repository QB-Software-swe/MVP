package it.qbsoftware.business.ports.in.jmap.utility;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public interface ResultReferenceResolverPort {
    public String[] resolve(final InvocationResultReferencePort resultReferenceResolverPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
