package it.qbsoftware.domain.CallableMethods;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;

public class CallableUnknownMethodCall extends AbstractCallableMethodCall{
    private MethodCall methodCall;

    public CallableUnknownMethodCall(MethodCall methodCall, ListMultimap<String, Response.Invocation> previousResponses) {
        super(previousResponses);
        this.methodCall = methodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        return new MethodResponse[] {new UnknownMethodMethodErrorResponse()};
    }
    
}
