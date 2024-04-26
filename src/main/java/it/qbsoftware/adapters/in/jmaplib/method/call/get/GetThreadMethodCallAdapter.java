package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class GetThreadMethodCallAdapter implements GetThreadMethodCallPort {
    private GetThreadMethodCall getThreadMethodCall;

    public GetThreadMethodCallAdapter(final GetThreadMethodCall getThreadMethodCall) {
        this.getThreadMethodCall = getThreadMethodCall;
    }

    @Override
    public String accountId() {
        return getThreadMethodCall.getAccountId();
    }

    @Override
    public String[] getIds() {
        return getThreadMethodCall.getIds();
    }

    @Override
    public InvocationResultReferencePort getIdsReference() {
        return new InvocationResultReferenceAdapter(getThreadMethodCall.getIdsReference());
    }

    @Override
    public String[] getProperties() {
        return getThreadMethodCall.getProperties();
    }

}
