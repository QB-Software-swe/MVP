package it.qbsoftware.domain.command.set;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

public class SetEmailCommand implements MethodCallCommand {
    private SetEmailMethodCall setEmailMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public SetEmailCommand(SetEmailMethodCall setEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.setEmailMethodCall = setEmailMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }    
}
