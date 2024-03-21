package it.qbsoftware.domain.Handlers.GetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethods.AbstractCallableMethodCall;
import it.qbsoftware.domain.Handlers.HandlerRequest;
import it.qbsoftware.domain.Handlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.standard.GetMethodCall;

public class GetMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMethodCall) {
            // CoR
            GetMethodCallHandler getMethodCallHandler = new GetMethodCallHandler();


            return getMethodCallHandler.handle(handlerRequest);
        }

        return super.handle(handlerRequest);
    }
}
