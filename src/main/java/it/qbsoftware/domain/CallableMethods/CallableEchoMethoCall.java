package it.qbsoftware.domain.CallableMethods;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;

public class CallableEchoMethoCall extends AbstractCallableMethodCall {
    private EchoMethodCall echoMethodCall;

    public CallableEchoMethoCall(EchoMethodCall echoMethodCall, ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.echoMethodCall = echoMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO
        return new MethodResponse[] { new EchoMethodResponse(echoMethodCall.getLibraryName()) };
    }

}
