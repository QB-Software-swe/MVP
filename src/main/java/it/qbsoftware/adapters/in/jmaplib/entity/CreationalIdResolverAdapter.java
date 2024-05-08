package it.qbsoftware.adapters.in.jmaplib.entity;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import it.qbsoftware.adapters.in.guava.ListMultimapAdapter;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.mock.server.CreationIdResolver;

public class CreationalIdResolverAdapter implements CreationIdResolverPort {
    @Override
    public String resolveIfNecessary(
            final String id,
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

        return CreationIdResolver.resolveIfNecessary(id, omega);
    }
}
