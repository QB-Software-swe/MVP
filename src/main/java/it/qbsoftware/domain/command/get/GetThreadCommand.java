package it.qbsoftware.domain.command.get;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class GetThreadCommand implements MethodCallCommand {
    private GetThreadMethodCall getThreadMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public GetThreadCommand(GetThreadMethodCall getThreadMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.getThreadMethodCall = getThreadMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
