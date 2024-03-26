package it.qbsoftware.domain.callhandler.set;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.method.call.standard.SetMethodCall;

public class SetMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof SetMethodCall) {
            SetEmailMethodCallHandler setEmailMethodCallHandler = new SetEmailMethodCallHandler();
            SetMailboxMethodCallHandler setMailboxMethodCallHandler = new SetMailboxMethodCallHandler();

            setEmailMethodCallHandler.setNext(setMailboxMethodCallHandler);

            return setEmailMethodCallHandler.handle(handlerRequest);
        }

        return super.handle(handlerRequest);
    }
}
