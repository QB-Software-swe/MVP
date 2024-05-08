package it.qbsoftware.business.ports.in.jmap.util;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;

public interface ResultReferenceResolverPort {
    public String[] resolve(
            final InvocationResultReferencePort resultReferenceResolverPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
