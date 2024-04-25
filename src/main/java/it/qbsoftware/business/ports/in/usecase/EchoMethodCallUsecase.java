package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponsePort;

public interface EchoMethodCallUsecase {
    public EchoMethodResponsePort call(EchoMethodCallPort echoMethodCall);
}
