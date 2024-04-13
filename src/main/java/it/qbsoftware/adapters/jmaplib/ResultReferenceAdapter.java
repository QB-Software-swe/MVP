package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResultReferencePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import rs.ltt.jmap.mock.server.ResultReferenceResolver;
import rs.ltt.jmap.common.Request.Invocation.ResultReference;
import rs.ltt.jmap.common.method.MethodCall;

public class ResultReferenceAdapter implements ResultReferencePort{

    //TODO ALE: ResultReferenceREsolver
    private final ResultReferenceResolver resultReference;

    public ResultReferenceAdapter(ResultReference resultReference) {
        this.resultReference = resultReference;
    }

    @Override
    public String[] resolve(InvocationResultReferencePort resultReference,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
                return this.resultReference.resolve();
    }

}
