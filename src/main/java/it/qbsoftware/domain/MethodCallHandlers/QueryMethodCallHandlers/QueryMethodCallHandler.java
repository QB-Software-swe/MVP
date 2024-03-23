package it.qbsoftware.domain.MethodCallHandlers.QueryMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.standard.QueryMethodCall;

public class QueryMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryMethodCall) {
            QueryEmailMethodCallHandler queryEmailMethodCallHandler = new QueryEmailMethodCallHandler();
            QueryMailboxMethodCallHandler queryMailboxMethodCallHandler = new QueryMailboxMethodCallHandler();

            queryEmailMethodCallHandler.setNext(queryMailboxMethodCallHandler);

            return queryEmailMethodCallHandler.handle(handlerRequest);
        }
        return super.handle(handlerRequest);
    }

}
