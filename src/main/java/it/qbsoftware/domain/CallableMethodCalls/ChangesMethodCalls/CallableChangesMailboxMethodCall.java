package it.qbsoftware.domain.CallableMethodCalls.ChangesMethodCalls;

import com.google.common.collect.ListMultimap;

import it.qbsoftware.domain.CallableMethodCalls.AbstractCallableMethodCall;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

public class CallableChangesMailboxMethodCall extends AbstractCallableMethodCall {
    private ChangesMailboxMethodCall changesMailboxMethodCall;

    public CallableChangesMailboxMethodCall(ChangesMailboxMethodCall changesMailboxMethodCall,
            ListMultimap<String, Invocation> previousResponses) {
        super(previousResponses);
        this.changesMailboxMethodCall = changesMailboxMethodCall;
    }

    @Override
    public MethodResponse[] call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

}
