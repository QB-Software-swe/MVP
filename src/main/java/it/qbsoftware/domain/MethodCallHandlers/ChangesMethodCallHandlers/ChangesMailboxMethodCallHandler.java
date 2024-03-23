package it.qbsoftware.domain.MethodCallHandlers.ChangesMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.ChangesMethodCalls.CallableChangesMailboxMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

public class ChangesMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof ChangesMailboxMethodCall changesMailboxMethodCall) {
            return new CallableChangesMailboxMethodCall(changesMailboxMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
