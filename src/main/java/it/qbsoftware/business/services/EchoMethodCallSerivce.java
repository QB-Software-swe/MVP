package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;

public class EchoMethodCallSerivce implements EchoMethodCallUsecase {
    EchoMethodResponseBuilderPort echoMethodResponseBuilder;

    public EchoMethodCallSerivce(EchoMethodResponseBuilderPort echoMethodResponseBuilder) {
        this.echoMethodResponseBuilder = echoMethodResponseBuilder;
    }

    @Override
    public MethodResponsePort[] call(EchoMethodCallPort echoMethodCall) {
        return new MethodResponsePort[] {
                echoMethodResponseBuilder.payload(echoMethodCall.payload()).build()
        };
    }
}
