package it.qbsoftware.adapters.in.jmaplib.method.response;

import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import rs.ltt.jmap.common.method.MethodResponse;

public class MethodResponseAdapter implements MethodResponsePort {
    private MethodResponse methodResponse;

    public MethodResponseAdapter(final MethodResponse methodResponse) {
        this.methodResponse = methodResponse;
    }

    public MethodResponse adaptee() {
        return methodResponse;
    }
}
