package it.qbsoftware.adapters.in.jmaplib.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import it.qbsoftware.adapters.in.guava.ListMultimapAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ResponseInvocationAdapter;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.mock.server.ResultReferenceResolver;

public class ResultReferenceResolverAdapter implements ResultReferenceResolverPort {

    @Override
    public String[] resolve(
            final InvocationResultReferencePort resultReferenceResolverPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        ListMultimap<String, Response.Invocation> omega = ArrayListMultimap.create();
        var phi =
                ((ListMultimapAdapter<String, ResponseInvocationPort>) previousResponses).adaptee();

        for (var xhi : phi.asMap().entrySet()) {
            var zeta =
                    xhi.getValue().stream()
                            .map(lambda -> ((ResponseInvocationAdapter) lambda).adaptee())
                            .collect(Collectors.toList());
            omega.putAll(xhi.getKey(), zeta);
        }

        return ResultReferenceResolver.resolve(
                ((InvocationResultReferenceAdapter) resultReferenceResolverPort)
                        .invocationResultReference(),
                omega);
    }
}
