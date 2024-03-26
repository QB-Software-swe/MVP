package it.qbsoftware.domain.callhandler.changes;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.changes.ChangesThreadCommand;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

public class ChangesThreadMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesThreadMethodCall changesThreadMethodCall) {
            return new ChangesThreadCommand(changesThreadMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
