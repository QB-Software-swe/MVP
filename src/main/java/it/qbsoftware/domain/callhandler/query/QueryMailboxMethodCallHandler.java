package it.qbsoftware.domain.callhandler.query;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.query.QueryMailboxCommand;
import rs.ltt.jmap.common.method.call.mailbox.QueryMailboxMethodCall;

public class QueryMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryMailboxMethodCall queryMailboxMethodCall) {
            return new QueryMailboxCommand(queryMailboxMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
