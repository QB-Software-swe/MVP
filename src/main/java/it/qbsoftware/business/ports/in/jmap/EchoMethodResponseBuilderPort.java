package it.qbsoftware.business.ports.in.jmap;

public interface EchoMethodResponseBuilderPort {
    public MethodResponsePort build();
    public EchoMethodResponseBuilderPort payload(String payload);
}
