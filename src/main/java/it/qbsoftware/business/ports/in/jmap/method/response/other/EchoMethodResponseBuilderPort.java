package it.qbsoftware.business.ports.in.jmap.method.response.other;

import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface EchoMethodResponseBuilderPort {
    public MethodResponsePort build();
    public EchoMethodResponseBuilderPort payload(String payload);
}
