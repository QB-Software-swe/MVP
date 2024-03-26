package it.qbsoftware.domain.callhandler.changes;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.changes.ChangesMailboxCommand;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

public class ChangesMailboxMethodCallHandler extends MethodCallHandlerBase {
    @Override
    public MethodCallCommand handle(HandlerRequest handlerRequest) {
        if(handlerRequest.methodCall() instanceof ChangesMailboxMethodCall changesMailboxMethodCall) {
            return new ChangesMailboxCommand(changesMailboxMethodCall, handlerRequest.previousResponses());
        }
        return super.handle(handlerRequest);
    }
}
