package it.qbsoftware.domain.command;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;

public class UnknownCommand implements MethodCallCommand {
    private MethodCall methodCall;
    private ListMultimap<String, Response.Invocation> previousResponses;

    public UnknownCommand(MethodCall methodCall, ListMultimap<String, Response.Invocation> previousResponses) {
        this.methodCall = methodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }    
}
