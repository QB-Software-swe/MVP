package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResultReferencePort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import rs.ltt.jmap.mock.server.ResultReferenceResolver;
import rs.ltt.jmap.common.Request.Invocation.ResultReference;
import rs.ltt.jmap.common.method.MethodCall;

public class ResultReferenceAdapter implements ResultReferencePort{
    private final ResultReference resultReference;

    public ResultReferenceAdapter(ResultReference resultReference) {
        this.resultReference = resultReference;
    }

    @Override
    public String[] resolve(InvocationResultReferencePort resultReference,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
                return ResultReferenceResolver.resolve();
    }

    /*@Override
    public String getId() {
        return resultReference.getId();
    }

    @Override
    public String getPath() {
        return resultReference.getPath();
    }

    @Override
    public Class<? extends MethodCall> getClazz() {
        return resultReference.getClazz();
    }*/

}
