package it.qbsoftware.domain.callhandler;

import it.qbsoftware.domain.command.MethodCallCommand;

public interface MethodCallHandler {
    void setNext(MethodCallHandler handler);

    MethodCallCommand handle(HandlerRequest handlerRequest);
}
