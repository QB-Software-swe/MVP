package it.qbsoftware.domain.callhandler.query;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.query.QueryEmailCommand;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;

public class QueryEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryEmailMethodCall queryEmailMethodCall) {
            return new QueryEmailCommand(queryEmailMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}