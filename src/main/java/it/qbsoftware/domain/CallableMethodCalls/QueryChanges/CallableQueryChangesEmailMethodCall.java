package it.qbsoftware.domain.CallableMethodCalls.QueryChanges;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.QueryChangesEmailMethodCall;

public class CallableQueryChangesEmailMethodCall extends AbstractCallableMethodCall {
    private QueryChangesEmailMethodCall queryChangesEmailMethodCall;

    public CallableQueryChangesEmailMethodCall(QueryChangesEmailMethodCall queryChangesEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.queryChangesEmailMethodCall = queryChangesEmailMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
    
}
