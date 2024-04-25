package it.qbsoftware.adapters.in.jmaplib.method.response.other;

import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponsePort;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;

public class EchoMethodResponseAdapter implements EchoMethodResponsePort {
    private final EchoMethodResponse echoMethodResponse;

    public EchoMethodResponseAdapter(final EchoMethodResponse echoMethodResponse) {
        this.echoMethodResponse = echoMethodResponse;
    }

    public EchoMethodResponse adaptee() {
        return this.echoMethodResponse;
    }
}
