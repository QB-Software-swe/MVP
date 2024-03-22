package it.qbsoftware.domain.CallableMethodCalls.SetMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

public class CallableSetEmailMethodCall extends AbstractCallableMethodCall {
    private SetEmailMethodCall setEmailMethodCall;

    public CallableSetEmailMethodCall(SetEmailMethodCall setEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.setEmailMethodCall = setEmailMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
