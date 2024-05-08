package it.qbsoftware.business.domain.util.get;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;

public interface CreationIdResolverPort {
    public String resolveIfNecessary(
            final String id,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
