package it.qbsoftware.domain.command.query;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;

public class QueryEmailCommand implements MethodCallCommand {
    private QueryEmailMethodCall queryEmailMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public QueryEmailCommand(QueryEmailMethodCall queryEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.queryEmailMethodCall = queryEmailMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
