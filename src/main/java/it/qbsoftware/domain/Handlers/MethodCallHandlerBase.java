package it.qbsoftware.domain.Handlers;

import it.qbsoftware.domain.CallableMethods.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethods.UnknownMethodCall;

public class MethodCallHandlerBase implements MethodCallHandler {
    private MethodCallHandler next = null;

    @Override
    public void setNext(MethodCallHandler handler) {
        next = handler;
    }

    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        return next != null ? next.handle(handlerRequest) : new UnknownMethodCall(handlerRequest.methodCall(), handlerRequest.previousResponses());
    }
}
