package it.qbsoftware.adapters.in.jmaplib.method.response;

import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import rs.ltt.jmap.common.method.MethodResponse;

public class MethodResponseAdapter implements MethodResponsePort {
    MethodResponse methodResponse;

    public MethodResponseAdapter(MethodResponse methodResponse) {
        this.methodResponse = methodResponse;
    }

    //FIXME
    public MethodResponse methodResponse() {
        return methodResponse;
    }
}
