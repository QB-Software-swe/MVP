package it.qbsoftware.domain.CallableMethodCalls.QueryMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;

public class CallableQueryEmailMethodCall extends AbstractCallableMethodCall {
    private QueryEmailMethodCall queryEmailMethodCall;

    public CallableQueryEmailMethodCall(QueryEmailMethodCall queryEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.queryEmailMethodCall = queryEmailMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

}
