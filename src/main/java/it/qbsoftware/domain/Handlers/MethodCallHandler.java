package it.qbsoftware.domain.Handlers;

import it.qbsoftware.domain.CallableMethods.AbstractCallableMethodCall;

public interface MethodCallHandler {
    void setNext(MethodCallHandler handler);

    AbstractCallableMethodCall handle(HandlerRequest handlerRequest);
}
