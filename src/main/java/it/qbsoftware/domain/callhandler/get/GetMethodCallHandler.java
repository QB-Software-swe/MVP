package it.qbsoftware.domain.callhandler.get;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.method.call.standard.GetMethodCall;

public class GetMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
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
