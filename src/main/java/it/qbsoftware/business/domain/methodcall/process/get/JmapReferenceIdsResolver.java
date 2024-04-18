package it.qbsoftware.business.domain.methodcall.process.get;

import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;

public class JmapReferenceIdsResolver implements GetReferenceIdsResolver {
    final ResultReferenceResolverPort resultReferenceResolverPort;

    public JmapReferenceIdsResolver(final ResultReferenceResolverPort resultReferenceResolverPort) {
        this.resultReferenceResolverPort = resultReferenceResolverPort;
    }

    @Override
    public String[] resolve(final GetMethodCallPort getMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidResultReferenceExecption {

        final InvocationResultReferencePort invocationResultReferencePort = getMethodCallPort.getIdsReference();

        if (invocationResultReferencePort != null) {
            try {
                return resultReferenceResolverPort.resolve(getMethodCallPort.getIdsReference(), previousResponses);
            } catch (final Exception exception) {
                throw new InvalidResultReferenceExecption();
            }
        }

        return getMethodCallPort.getIds();
    }

}
