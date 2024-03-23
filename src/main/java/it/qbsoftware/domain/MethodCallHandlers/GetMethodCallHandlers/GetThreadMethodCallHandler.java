package it.qbsoftware.domain.MethodCallHandlers.GetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls.CallableGetThreadMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class GetThreadMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof GetThreadMethodCall getThreadMethodCall) {
            return new CallableGetThreadMethodCall(getThreadMethodCall, handlerRequest.previousResponses());
        }

        return super.handle(handlerRequest);
    }
}