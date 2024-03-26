package it.qbsoftware.domain.callhandler.get;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.get.GetMailboxCommand;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {
            return new GetMailboxCommand(getMailboxMethodCall, handlerRequest.previousResponses());
        }

        return super.handle(handlerRequest);
    }
}
