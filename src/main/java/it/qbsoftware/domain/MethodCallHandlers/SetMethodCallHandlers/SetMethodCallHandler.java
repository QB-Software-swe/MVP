package it.qbsoftware.domain.MethodCallHandlers.SetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.standard.SetMethodCall;

public class SetMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof SetMethodCall) {
            //TODO:
        }

        return super.handle(handlerRequest);
    }
}
