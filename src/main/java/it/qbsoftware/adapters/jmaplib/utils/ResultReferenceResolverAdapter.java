package it.qbsoftware.adapters.jmaplib.utils;

import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import it.qbsoftware.adapters.guava.ListMultimapAdapter;
import it.qbsoftware.adapters.jmaplib.InvocationResultReferenceAdapter;
import it.qbsoftware.adapters.jmaplib.ResponseInvocationAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.mock.server.ResultReferenceResolver;

//FIXME: tic tac tic tac tic tac
//TODO: cambiare i nomi
public class ResultReferenceResolverAdapter implements ResultReferenceResolverPort {

    @Override
    public String[] resolve(InvocationResultReferencePort resultReferenceResolverPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        ListMultimap<String, Response.Invocation> omega = ArrayListMultimap.create();
        var phi = ((ListMultimapAdapter<String, ResponseInvocationPort>) previousResponses).listMultimap();

        for (var xhi : phi.asMap().entrySet()) {
            var zeta = xhi.getValue().stream().map(lambda -> ((ResponseInvocationAdapter) lambda).invocation())
                    .collect(Collectors.toList());
            omega.putAll(xhi.getKey(), zeta);
        }

        return ResultReferenceResolver.resolve(
                ((InvocationResultReferenceAdapter) resultReferenceResolverPort).invocationResultReference(), omega);
    }

}
