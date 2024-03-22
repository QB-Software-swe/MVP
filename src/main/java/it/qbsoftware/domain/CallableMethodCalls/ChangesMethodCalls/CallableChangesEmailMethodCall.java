package it.qbsoftware.domain.CallableMethodCalls.ChangesMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class CallableChangesEmailMethodCall extends AbstractCallableMethodCall {
    private ChangesEmailMethodCall changesEmailMethodCall;
    
    public CallableChangesEmailMethodCall(ChangesEmailMethodCall changesEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.changesEmailMethodCall = changesEmailMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
