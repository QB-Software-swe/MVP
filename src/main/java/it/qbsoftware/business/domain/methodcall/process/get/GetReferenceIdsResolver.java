package it.qbsoftware.business.domain.methodcall.process.get;

import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMethodCallPort;

public interface GetReferenceIdsResolver {
    public String[] resolve(final GetMethodCallPort getMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidResultReferenceExecption;
}
