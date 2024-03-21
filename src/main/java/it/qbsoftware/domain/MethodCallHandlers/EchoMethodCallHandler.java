package it.qbsoftware.domain.MethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethodCalls.CallableEchoMethoCall;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof EchoMethodCall echoMethodCall) {
            return new CallableEchoMethoCall(echoMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
