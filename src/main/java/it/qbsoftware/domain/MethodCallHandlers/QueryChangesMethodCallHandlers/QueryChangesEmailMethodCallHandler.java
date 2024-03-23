package it.qbsoftware.domain.MethodCallHandlers.QueryChangesMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.QueryChanges.CallableQueryChangesEmailMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.email.QueryChangesEmailMethodCall;

public class QueryChangesEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryChangesEmailMethodCall queryChangesEmailMethodCall) {
            return new CallableQueryChangesEmailMethodCall(queryChangesEmailMethodCall,
                    handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }

}
