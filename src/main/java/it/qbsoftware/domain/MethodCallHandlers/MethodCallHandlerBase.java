package it.qbsoftware.domain.MethodCallHandlers;

import it.qbsoftware.domain.CallableMethods.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethods.CallableUnknownMethodCall;

public abstract class MethodCallHandlerBase implements MethodCallHandler {
    private MethodCallHandler next = null;

    @Override
    public void setNext(MethodCallHandler handler) {
        next = handler;
    }

    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        return next != null ? next.handle(handlerRequest)
                : new CallableUnknownMethodCall(handlerRequest.methodCall(), handlerRequest.previousResponses());
    }
}
