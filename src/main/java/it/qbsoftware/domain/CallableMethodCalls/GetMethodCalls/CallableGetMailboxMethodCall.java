package it.qbsoftware.domain.CallableMethodCalls.GetMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class CallableGetMailboxMethodCall extends AbstractCallableMethodCall {
    private GetMailboxMethodCall getMailboxMethodCall;

    public CallableGetMailboxMethodCall(GetMailboxMethodCall getMailboxMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.getMailboxMethodCall = getMailboxMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
