package it.qbsoftware.domain.MethodCallHandlers.GetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls.CallableGetMailboxMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {
            return new CallableGetMailboxMethodCall(getMailboxMethodCall, handlerRequest.previousResponses());
        }

        return super.handle(handlerRequest);
    }
}
