package it.qbsoftware.domain.CallableMethodCalls.QueryMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.QueryMailboxMethodCall;

public class CallableQueryMailboxMethodCall extends AbstractCallableMethodCall {
    private QueryMailboxMethodCall queryMailboxMethodCall;

    public CallableQueryMailboxMethodCall(QueryMailboxMethodCall queryMailboxMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.queryMailboxMethodCall = queryMailboxMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

}
