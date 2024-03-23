package it.qbsoftware.domain.MethodCallHandlers.ChangesMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.ChangesMethodCalls.CallableChangesThreadMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

public class ChangesThreadMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesThreadMethodCall changesThreadMethodCall) {
            return new CallableChangesThreadMethodCall(changesThreadMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
