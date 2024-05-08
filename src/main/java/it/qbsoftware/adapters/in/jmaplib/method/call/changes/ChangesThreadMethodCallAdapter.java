package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

public class ChangesThreadMethodCallAdapter implements ChangesThreadMethodCallPort {
    private ChangesThreadMethodCall changesThreadMethodCall;

    public ChangesThreadMethodCallAdapter(final ChangesThreadMethodCall changesThreadMethodCall) {
        this.changesThreadMethodCall = changesThreadMethodCall;
    }

    @Override
    public String accountId() {
        return changesThreadMethodCall.getAccountId();
    }

    @Override
    public String getSinceState() {
        return changesThreadMethodCall.getSinceState();
    }

    @Override
    public Long getMaxChanges() {
        return changesThreadMethodCall.getMaxChanges();
    }
}
