package it.qbsoftware.application.controllers;

import rs.ltt.jmap.common.method.MethodResponse;

public interface ControllerHandler {
    public void setNext(ControllerHandler handler);

    public MethodResponse[] handle(HandlerRequest handlerRequest);
}
