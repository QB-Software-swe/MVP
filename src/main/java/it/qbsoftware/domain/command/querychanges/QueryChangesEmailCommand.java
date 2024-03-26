package it.qbsoftware.domain.command.querychanges;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.QueryChangesEmailMethodCall;

public class QueryChangesEmailCommand implements MethodCallCommand {
    private QueryChangesEmailMethodCall queryChangesEmailMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public QueryChangesEmailCommand(QueryChangesEmailMethodCall queryChangesEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.queryChangesEmailMethodCall = queryChangesEmailMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
