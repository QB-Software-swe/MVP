package it.qbsoftware.adapters.in.jmaplib.method.call.other;

import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallAdapter implements EchoMethodCallPort {
    private final EchoMethodCall echoMethodCall;

    public EchoMethodCallAdapter(final EchoMethodCall echoMethodCall) {
        this.echoMethodCall = echoMethodCall;
    }

    @Override
    public String payload() {
        return echoMethodCall.getLibraryName();
    }
}
