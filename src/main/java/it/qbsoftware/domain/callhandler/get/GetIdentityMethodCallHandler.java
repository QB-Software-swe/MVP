package it.qbsoftware.domain.callhandler.get;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.get.GetIdentityCommand;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class GetIdentityMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {
            return new GetIdentityCommand(getIdentityMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
