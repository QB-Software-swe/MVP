package it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class CallableGetEmailMethodCall extends AbstractCallableMethodCall {
    private GetEmailMethodCall getEmailMethodCall;

    public CallableGetEmailMethodCall(GetEmailMethodCall getEmailMethodCall,
            ListMultimap<String, Response.Invocation> previousResponses) {
        super(previousResponses);
        this.getEmailMethodCall = getEmailMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

}
