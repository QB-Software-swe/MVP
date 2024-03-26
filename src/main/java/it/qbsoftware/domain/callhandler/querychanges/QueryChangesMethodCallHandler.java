package it.qbsoftware.domain.callhandler.querychanges;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.method.call.standard.QueryChangesMethodCall;

public class QueryChangesMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryChangesMethodCall) {
            QueryChangesEmailMethodCallHandler queryChangesEmailMethodCallHandler = new QueryChangesEmailMethodCallHandler();

            return queryChangesEmailMethodCallHandler.handle(handlerRequest);
        }

        return super.handle(handlerRequest);
    }
}
