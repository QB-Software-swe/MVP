package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;

public class EchoMethodCallSerivce implements EchoMethodCallUsecase {
    final EchoMethodResponseBuilderPort echoMethodResponseBuilder;

    public EchoMethodCallSerivce(final EchoMethodResponseBuilderPort echoMethodResponseBuilder) {
        this.echoMethodResponseBuilder = echoMethodResponseBuilder;
    }

    @Override
    public MethodResponsePort[] call(EchoMethodCallPort echoMethodCall) {
        return new MethodResponsePort[] {
                echoMethodResponseBuilder.payload(echoMethodCall.payload()).build()
        };
    }
}
