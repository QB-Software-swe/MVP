package it.qbsoftware.domain.callhandler.set;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.set.SetEmailCommand;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

public class SetEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof SetEmailMethodCall setEmailMethodCall) {
            return new SetEmailCommand(setEmailMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
