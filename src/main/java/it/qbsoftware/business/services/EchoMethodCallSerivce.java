package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;

public class EchoMethodCallSerivce implements EchoMethodCallUsecase {
    final EchoMethodResponseBuilderPort echoMethodResponseBuilder;

    public EchoMethodCallSerivce(final EchoMethodResponseBuilderPort echoMethodResponseBuilder) {
        this.echoMethodResponseBuilder = echoMethodResponseBuilder;
    }

    @Override
    public EchoMethodResponsePort call(EchoMethodCallPort echoMethodCall) {
        return echoMethodResponseBuilder.payload(echoMethodCall.payload()).build();
    }
}
