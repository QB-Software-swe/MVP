package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class ChangesEmailMethodCallAdapter implements ChangesEmailMethodCallPort {
    private ChangesEmailMethodCall changesEmailMethodCall;

    public ChangesEmailMethodCallAdapter(final ChangesEmailMethodCall changesEmailMethodCall) {
        this.changesEmailMethodCall = changesEmailMethodCall;
    }

    @Override
    public String accountId() {
        return changesEmailMethodCall.getAccountId();
    }

    @Override
    public String getSinceState() {
        return changesEmailMethodCall.getSinceState();
    }

    @Override
    public Long getMaxChanges() {
        return changesEmailMethodCall.getMaxChanges();
    }

}
