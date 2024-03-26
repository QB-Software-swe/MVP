package it.qbsoftware.domain.callhandler.get;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.get.GetEmailCommand;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {
            return new GetEmailCommand(getEmailMethodCall, handlerRequest.previousResponses());
        }

        return super.handle(handlerRequest);
    }
}
