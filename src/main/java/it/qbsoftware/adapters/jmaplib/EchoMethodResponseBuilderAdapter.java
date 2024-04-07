package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse.EchoMethodResponseBuilder;

public class EchoMethodResponseBuilderAdapter implements EchoMethodResponseBuilderPort {
    EchoMethodResponseBuilder echoMethodResponseBuilder;

    @Override
    public MethodResponsePort build() {
        return new MethodResponseAdapter(echoMethodResponseBuilder.build());
    }

    @Override
    public EchoMethodResponseBuilderPort payload(String payload) {
        echoMethodResponseBuilder.libraryName(payload);
        return this;
    }

}