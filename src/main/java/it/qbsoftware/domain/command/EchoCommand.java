package it.qbsoftware.domain.command;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoCommand implements MethodCallCommand {
    private EchoMethodCall echoMethodCall;
    ListMultimap<String, Invocation> previousResponses;

    public EchoCommand(EchoMethodCall echoMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.echoMethodCall = echoMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
