package it.qbsoftware.domain.MethodCallHandlers.QueryMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.QueryMethodCalls.CallableQueryMailboxMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.mailbox.QueryMailboxMethodCall;

public class QueryMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryMailboxMethodCall queryMailboxMethodCall) {
            return new CallableQueryMailboxMethodCall(queryMailboxMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
