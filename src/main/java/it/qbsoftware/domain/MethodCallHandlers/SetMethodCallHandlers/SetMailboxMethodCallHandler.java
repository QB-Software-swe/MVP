package it.qbsoftware.domain.MethodCallHandlers.SetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.SetMethodCalls.CallableSetMailboxMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class SetMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof SetMailboxMethodCall setMailboxMethodCall) {
            return new CallableSetMailboxMethodCall(setMailboxMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
