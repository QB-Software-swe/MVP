package it.qbsoftware.business.domain.util.get;

import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.jmap.GetMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

//FIXME: rinominare
public interface GetMethodCallSetup<EntityType> {
    public GetRetrivedEntity<EntityType> getEntity(final GetMethodCallPort getMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidResultReferenceExecption;
}
