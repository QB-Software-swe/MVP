package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;

public class EchoMethodCallSerivce {
    EchoMethodResponseBuilderPort echoMethodResponseBuilder;

    public EchoMethodCallSerivce(EchoMethodResponseBuilderPort echoMethodResponseBuilder) {
        this.echoMethodResponseBuilder = echoMethodResponseBuilder;
    }

    public MethodResponsePort[] call(EchoMethodCallPort echoMethodCall) {
        return new MethodResponsePort[] {
                echoMethodResponseBuilder.payload(echoMethodCall.payload()).build()
        };
    }
}
