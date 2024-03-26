package it.qbsoftware.domain.callhandler;

import it.qbsoftware.domain.command.EchoCommand;
import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof EchoMethodCall echoMethodCall) {
            return new EchoCommand(echoMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
