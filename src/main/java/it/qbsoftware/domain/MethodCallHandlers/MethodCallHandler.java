package it.qbsoftware.domain.MethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;

public interface MethodCallHandler {
    void setNext(MethodCallHandler handler);

    AbstractCallableMethodCall handle(HandlerRequest handlerRequest);
}
