package it.qbsoftware.domain.callhandler.get;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.get.GetThreadCommand;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class GetThreadMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof GetThreadMethodCall getThreadMethodCall) {
            return new GetThreadCommand(getThreadMethodCall, handlerRequest.previousResponses());
        }

        return super.handle(handlerRequest);
    }
}