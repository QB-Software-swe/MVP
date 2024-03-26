package it.qbsoftware.domain.command.changes;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.command.MethodCallCommand;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

public class ChangesThreadCommand implements MethodCallCommand {
    private ChangesThreadMethodCall changesThreadMethodCall;
    private ListMultimap<String, Invocation> previousResponses;

    public ChangesThreadCommand(ChangesThreadMethodCall changesThreadMethodCall, ListMultimap<String, Invocation> previousResponses) {
        this.changesThreadMethodCall = changesThreadMethodCall;
        this.previousResponses = previousResponses;
    }

    @Override
    public MethodResponse[] execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
