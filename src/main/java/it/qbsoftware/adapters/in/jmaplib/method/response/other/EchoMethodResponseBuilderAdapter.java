package it.qbsoftware.adapters.in.jmaplib.method.response.other;

import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponsePort;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse.EchoMethodResponseBuilder;

public class EchoMethodResponseBuilderAdapter implements EchoMethodResponseBuilderPort {
    private final EchoMethodResponseBuilder echoMethodResponseBuilder;

    public EchoMethodResponseBuilderAdapter() {
        this.echoMethodResponseBuilder = EchoMethodResponse.builder();
    }

    @Override
    public EchoMethodResponsePort build() {
        return new EchoMethodResponseAdapter(echoMethodResponseBuilder.build());
    }

    @Override
    public EchoMethodResponseBuilderPort payload(String payload) {
        echoMethodResponseBuilder.libraryName(payload);
        return this;
    }
}
