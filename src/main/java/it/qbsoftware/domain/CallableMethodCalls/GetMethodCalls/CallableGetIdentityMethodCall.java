package it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class CallableGetIdentityMethodCall extends AbstractCallableMethodCall {
    private GetIdentityMethodCall getIdentityMethodCall;

    public CallableGetIdentityMethodCall(GetIdentityMethodCall getIdentityMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.getIdentityMethodCall = getIdentityMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
