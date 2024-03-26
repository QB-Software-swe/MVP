package it.qbsoftware.domain.callhandler.changes;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.changes.ChangesEmailCommand;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class ChangesEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof ChangesEmailMethodCall changesEmailMethodCall) {
            return new ChangesEmailCommand(changesEmailMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
