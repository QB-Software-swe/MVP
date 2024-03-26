package it.qbsoftware.domain.command.query;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.QueryMailboxMethodCall;

public class QueryMailboxCommand implements MethodCallCommand {
    private QueryMailboxMethodCall queryMailboxMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public QueryMailboxCommand(QueryMailboxMethodCall queryMailboxMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.queryMailboxMethodCall = queryMailboxMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
