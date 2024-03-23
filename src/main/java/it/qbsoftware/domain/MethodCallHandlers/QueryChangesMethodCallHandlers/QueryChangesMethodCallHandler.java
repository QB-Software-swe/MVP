package it.qbsoftware.domain.MethodCallHandlers.QueryChangesMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.standard.QueryChangesMethodCall;

public class QueryChangesMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryChangesMethodCall) {
            QueryChangesEmailMethodCallHandler queryChangesEmailMethodCallHandler = new QueryChangesEmailMethodCallHandler();

            return queryChangesEmailMethodCallHandler.handle(handlerRequest);
        }

        return super.handle(handlerRequest);
    }
}
