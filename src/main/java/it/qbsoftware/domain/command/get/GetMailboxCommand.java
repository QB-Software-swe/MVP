package it.qbsoftware.domain.command.get;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxCommand implements MethodCallCommand {
    private GetMailboxMethodCall getMailboxMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public GetMailboxCommand(GetMailboxMethodCall getMailboxMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.getMailboxMethodCall = getMailboxMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }   
}
