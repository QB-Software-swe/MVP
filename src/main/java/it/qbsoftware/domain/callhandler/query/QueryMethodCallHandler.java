package it.qbsoftware.domain.callhandler.query;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.method.call.standard.QueryMethodCall;

public class QueryMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryMethodCall) {
            QueryEmailMethodCallHandler queryEmailMethodCallHandler = new QueryEmailMethodCallHandler();
            QueryMailboxMethodCallHandler queryMailboxMethodCallHandler = new QueryMailboxMethodCallHandler();

            queryEmailMethodCallHandler.setNext(queryMailboxMethodCallHandler);

            return queryEmailMethodCallHandler.handle(handlerRequest);
        }
        return super.handle(handlerRequest);
    }

}
