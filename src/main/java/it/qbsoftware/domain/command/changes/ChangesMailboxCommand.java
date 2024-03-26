package it.qbsoftware.domain.command.changes;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

public class ChangesMailboxCommand implements MethodCallCommand {
    private ChangesMailboxMethodCall changesMailboxMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public ChangesMailboxCommand(ChangesMailboxMethodCall changesMailboxMethodCall,
            ListMultimap<String, Invocation> previousResponses) {
        this.changesMailboxMethodCall = changesMailboxMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
