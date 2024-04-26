package it.qbsoftware.business.ports.in.jmap.method.response.other;

public interface EchoMethodResponseBuilderPort {
    public EchoMethodResponsePort build();
    public EchoMethodResponseBuilderPort payload(String payload);
}
