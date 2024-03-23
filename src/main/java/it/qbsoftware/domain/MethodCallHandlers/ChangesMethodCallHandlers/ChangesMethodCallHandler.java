package it.qbsoftware.domain.MethodCallHandlers.ChangesMethodCallHandlers;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.standard.ChangesMethodCall;

public class ChangesMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public AbstractCallableMethodCall handle(HandlerRequest handlerRequest) {
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
