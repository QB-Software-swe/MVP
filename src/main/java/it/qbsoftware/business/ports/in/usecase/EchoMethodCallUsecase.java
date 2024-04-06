package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;

public interface EchoMethodCallUsecase {
    public MethodResponsePort[] call(EchoMethodCallPort echoMethodCall);
}
