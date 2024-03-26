package it.qbsoftware.domain.command.get;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailCommand implements MethodCallCommand {
    private GetEmailMethodCall getEmailMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public GetEmailCommand(GetEmailMethodCall getEmailMethodCall,
            ListMultimap<String, Response.Invocation> previousResponses) {
        this.getEmailMethodCall = getEmailMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
