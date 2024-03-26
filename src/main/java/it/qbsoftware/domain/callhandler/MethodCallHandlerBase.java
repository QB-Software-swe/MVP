package it.qbsoftware.domain.callhandler;

import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;

public abstract class MethodCallHandlerBase implements MethodCallHandler {
    private MethodCallHandler next = null;

    @Override
    public void setNext(MethodCallHandler handler) {
        next = handler;
    }

    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        return next != null ? next.handle(handlerRequest)
                : new UnknownCommand(handlerRequest.methodCall(), handlerRequest.previousResponses());
    }
}
