package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import rs.ltt.jmap.common.method.call.identity.ChangesIdentityMethodCall;

public class ChangesIdentityMethodCallAdapter implements ChangesIdentityMethodCallPort {
    private ChangesIdentityMethodCall changesIdentityMethodCall;

    public ChangesIdentityMethodCallAdapter(final ChangesIdentityMethodCall changesIdentityMethodCall) {
        this.changesIdentityMethodCall = changesIdentityMethodCall;
    }

    @Override
    public String accountId() {
        return changesIdentityMethodCall.getAccountId();
    }

    @Override
    public String getSinceState() {
        return changesIdentityMethodCall.getSinceState();
    }

    @Override
    public Long getMaxChanges() {
        return changesIdentityMethodCall.getMaxChanges();
    }

}
