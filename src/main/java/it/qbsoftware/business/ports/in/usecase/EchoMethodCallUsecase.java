package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface EchoMethodCallUsecase {
    public MethodResponsePort[] call(EchoMethodCallPort echoMethodCall);
}
