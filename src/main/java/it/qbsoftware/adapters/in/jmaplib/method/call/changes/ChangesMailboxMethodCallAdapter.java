package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMailboxMethodCallPort;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

public class ChangesMailboxMethodCallAdapter implements ChangesMailboxMethodCallPort {
    private ChangesMailboxMethodCall changesMailboxMethodCall;

    public ChangesMailboxMethodCallAdapter(
            final ChangesMailboxMethodCall changesMailboxMethodCall) {
        this.changesMailboxMethodCall = changesMailboxMethodCall;
    }

    @Override
    public String accountId() {
        return changesMailboxMethodCall.getAccountId();
    }

    @Override
    public String getSinceState() {
        return changesMailboxMethodCall.getSinceState();
    }

    @Override
    public Long getMaxChanges() {
        return changesMailboxMethodCall.getMaxChanges();
    }
}
