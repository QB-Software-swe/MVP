package it.qbsoftware.domain.MethodCallHandlers.GetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethods.AbstractCallableMethodCall;
import it.qbsoftware.domain.CallableMethods.CallableGetEmailMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {
            return new CallableGetEmailMethodCall(getEmailMethodCall, handlerRequest.previousResponses());
        }

        return super.handle(handlerRequest);
    }
}
