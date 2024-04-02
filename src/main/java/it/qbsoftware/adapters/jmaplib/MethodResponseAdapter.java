package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
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
