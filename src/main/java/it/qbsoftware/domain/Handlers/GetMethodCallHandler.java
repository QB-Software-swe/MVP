package it.qbsoftware.domain.Handlers;

import it.qbsoftware.domain.CallableMethods.AbstractCallableMethodCall;
import rs.ltt.jmap.common.method.call.standard.GetMethodCall;

public class GetMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof GetMethodCall) {
            //TODO
            return null;
        }

        return super.handle(handlerRequest);
    }
}
