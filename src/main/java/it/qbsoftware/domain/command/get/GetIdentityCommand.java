package it.qbsoftware.domain.command.get;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class GetIdentityCommand implements MethodCallCommand {
    private GetIdentityMethodCall getIdentityMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public GetIdentityCommand(GetIdentityMethodCall getIdentityMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.getIdentityMethodCall = getIdentityMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }    
}
