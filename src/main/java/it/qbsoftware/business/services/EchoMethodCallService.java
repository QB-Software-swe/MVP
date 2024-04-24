package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;

public class EchoMethodCallService implements EchoMethodCallUsecase {
    final EchoMethodResponseBuilderPort echoMethodResponseBuilder;

    public EchoMethodCallService(final EchoMethodResponseBuilderPort echoMethodResponseBuilder) {
        this.echoMethodResponseBuilder = echoMethodResponseBuilder;
    }

    @Override
    public MethodResponsePort[] call(EchoMethodCallPort echoMethodCall) {
        return new MethodResponsePort[] {
                echoMethodResponseBuilder.payload(echoMethodCall.payload()).build()
        };
    }
}
