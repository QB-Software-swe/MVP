package it.qbsoftware.application.controllers;

import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;

public abstract class ControllerHandlerBase implements ControllerHandler {
    private ControllerHandler nextHandler = null;

    @Override
    public void setNext(final ControllerHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (nextHandler != null) {
            return nextHandler.handle(handlerRequest);
        }

        return new MethodResponse[] {new UnknownMethodMethodErrorResponse()};
    }
}
