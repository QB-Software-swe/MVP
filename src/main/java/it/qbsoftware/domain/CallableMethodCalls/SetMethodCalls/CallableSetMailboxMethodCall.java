package it.qbsoftware.domain.CallableMethodCalls.SetMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class CallableSetMailboxMethodCall extends AbstractCallableMethodCall {
    private SetMailboxMethodCall setMailboxMethodCall;

    public CallableSetMailboxMethodCall(SetMailboxMethodCall setMailboxMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.setMailboxMethodCall = setMailboxMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
}
