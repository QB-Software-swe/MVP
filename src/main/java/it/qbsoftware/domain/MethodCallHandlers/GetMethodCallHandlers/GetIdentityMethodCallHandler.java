package it.qbsoftware.domain.MethodCallHandlers.GetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls.CallableGetIdentityMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class GetIdentityMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {
            return new CallableGetIdentityMethodCall(getIdentityMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
