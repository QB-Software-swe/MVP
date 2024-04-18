package it.qbsoftware.adapters.in.jmaplib.method.response.other;

import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse.EchoMethodResponseBuilder;

public class EchoMethodResponseBuilderAdapter implements EchoMethodResponseBuilderPort {
    EchoMethodResponseBuilder echoMethodResponseBuilder;

    public EchoMethodResponseBuilderAdapter(){
        this.echoMethodResponseBuilder = EchoMethodResponse.builder();
    }

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