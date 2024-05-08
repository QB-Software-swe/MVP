package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetIdentityMethodCallPort;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class GetIdentityMethodCallAdapter implements GetIdentityMethodCallPort {
    GetIdentityMethodCall getIdentityMethodCall;

    public GetIdentityMethodCallAdapter(GetIdentityMethodCall getIdentityMethodCall) {
        this.getIdentityMethodCall = getIdentityMethodCall;
    }

    @Override
    public String accountId() {
        return getIdentityMethodCall.getAccountId();
    }

    @Override
    public String[] getIds() {
        return getIdentityMethodCall.getIds();
    }

    @Override
    public InvocationResultReferencePort getIdsReference() {
        return getIdentityMethodCall.getIdsReference() != null
                ? new InvocationResultReferenceAdapter(getIdentityMethodCall.getIdsReference())
                : null;
    }

    @Override
    public String[] getProperties() {
        return getIdentityMethodCall.getProperties();
    }
}
