package it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class CallableGetThreadMethodCall extends AbstractCallableMethodCall{
    private GetThreadMethodCall getThreadMethodCall;

    public CallableGetThreadMethodCall(GetThreadMethodCall getThreadMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.getThreadMethodCall = getThreadMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
