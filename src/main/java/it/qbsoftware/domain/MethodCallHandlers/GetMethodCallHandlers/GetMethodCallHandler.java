package it.qbsoftware.domain.MethodCallHandlers.GetMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.standard.GetMethodCall;

public class GetMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMethodCall) {
            GetMailboxMethodCallHandler getMailboxMethodCallHandler = new GetMailboxMethodCallHandler();
            GetIdentityMethodCallHandler getIdentityMethodCallHandler = new GetIdentityMethodCallHandler();
            GetEmailMethodCallHandler getEmailMethodCallHandler = new GetEmailMethodCallHandler();
            GetThreadMethodCallHandler getThreadMethodCallHandler = new GetThreadMethodCallHandler();
            
            getMailboxMethodCallHandler.setNext(getIdentityMethodCallHandler);
            getIdentityMethodCallHandler.setNext(getEmailMethodCallHandler);
            getEmailMethodCallHandler.setNext(getThreadMethodCallHandler);

            return getMailboxMethodCallHandler.handle(handlerRequest);
        }

        return super.handle(handlerRequest);
    }
}
