package it.qbsoftware.domain.MethodCallHandlers.SetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.SetMethodCalls.CallableSetEmailMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

public class SetEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof SetEmailMethodCall setEmailMethodCall) {
            return new CallableSetEmailMethodCall(setEmailMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
