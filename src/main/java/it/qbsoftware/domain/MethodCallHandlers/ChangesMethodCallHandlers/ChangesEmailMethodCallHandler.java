package it.qbsoftware.domain.MethodCallHandlers.ChangesMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.ChangesMethodCalls.CallableChangesEmailMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class ChangesEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof ChangesEmailMethodCall changesEmailMethodCall) {
            return new CallableChangesEmailMethodCall(changesEmailMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
