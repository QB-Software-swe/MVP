package it.qbsoftware.domain.callhandler.querychanges;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.querychanges.QueryChangesEmailCommand;
import rs.ltt.jmap.common.method.call.email.QueryChangesEmailMethodCall;

public class QueryChangesEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryChangesEmailMethodCall queryChangesEmailMethodCall) {
            return new QueryChangesEmailCommand(queryChangesEmailMethodCall,
                    handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }

}
