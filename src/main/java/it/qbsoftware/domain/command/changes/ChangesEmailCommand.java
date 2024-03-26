package it.qbsoftware.domain.command.changes;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class ChangesEmailCommand implements MethodCallCommand {
    private ChangesEmailMethodCall changesEmailMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public ChangesEmailCommand(ChangesEmailMethodCall changesEmailMethodCall,
            ListMultimap<String, Invocation> previousResponses) {
        this.changesEmailMethodCall = changesEmailMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
