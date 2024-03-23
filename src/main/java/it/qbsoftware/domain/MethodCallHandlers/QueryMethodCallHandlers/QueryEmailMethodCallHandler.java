package it.qbsoftware.domain.MethodCallHandlers.QueryMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.QueryMethodCalls.CallableQueryEmailMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;

public class QueryEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryEmailMethodCall queryEmailMethodCall) {
            return new CallableQueryEmailMethodCall(queryEmailMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}