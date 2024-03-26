package it.qbsoftware.domain.callhandler.changes;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.method.call.standard.ChangesMethodCall;

public class ChangesMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesMethodCall) {
            ChangesMailboxMethodCallHandler changesMailboxMethodCallHandler = new ChangesMailboxMethodCallHandler();
            ChangesEmailMethodCallHandler changesEmailMethodCallHandler = new ChangesEmailMethodCallHandler();
            ChangesThreadMethodCallHandler changesThreadMethodCallHandler = new ChangesThreadMethodCallHandler();

            changesMailboxMethodCallHandler.setNext(changesEmailMethodCallHandler);
            changesEmailMethodCallHandler.setNext(changesThreadMethodCallHandler);

            return changesMailboxMethodCallHandler.handle(handlerRequest);
        }

        return super.handle(handlerRequest);
    }
}
