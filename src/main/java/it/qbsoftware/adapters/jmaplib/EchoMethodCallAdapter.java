package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallAdapter implements EchoMethodCallPort {
    EchoMethodCall echoMethodCall;

    public EchoMethodCallAdapter(EchoMethodCall echoMethodCall) {
        this.echoMethodCall = echoMethodCall;
    }

    @Override
    public String payload() {
        return echoMethodCall.getLibraryName();
    }

}
