package it.qbsoftware.application.controllers;

import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;

public abstract class ControllerHandlerBase implements ControllerHandler {
    ControllerHandler nextHandler;

    @Override
    public void setNext(ControllerHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        // TODO
        return new MethodResponse[] { new UnknownMethodMethodErrorResponse() };
    }
}
