package it.qbsoftware.domain.command.set;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class SetMailboxCommand implements MethodCallCommand {
    private SetMailboxMethodCall setMailboxMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public SetMailboxCommand(SetMailboxMethodCall setMailboxMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.setMailboxMethodCall = setMailboxMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
