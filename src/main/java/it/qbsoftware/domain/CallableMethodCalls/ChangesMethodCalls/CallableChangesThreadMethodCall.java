package it.qbsoftware.domain.CallableMethodCalls.ChangesMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

public class CallableChangesThreadMethodCall extends AbstractCallableMethodCall {
    private ChangesThreadMethodCall changesThreadMethodCall;

    public CallableChangesThreadMethodCall(ChangesThreadMethodCall changesThreadMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.changesThreadMethodCall = changesThreadMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
