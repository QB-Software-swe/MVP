package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import rs.ltt.jmap.common.method.call.submission.ChangesEmailSubmissionMethodCall;

public class ChangesEmailSubmissionMethodCallAdapter implements ChangesEmailSubmissionMethodCallPort {
    private final ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall;

    public ChangesEmailSubmissionMethodCallAdapter(
            final ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall) {
        this.changesEmailSubmissionMethodCall = changesEmailSubmissionMethodCall;
    }

    @Override
    public String accountId() {
        return changesEmailSubmissionMethodCall.getAccountId();
    }

    @Override
    public String getSinceState() {
        return changesEmailSubmissionMethodCall.getSinceState();
    }

    @Override
    public Long getMaxChanges() {
        return changesEmailSubmissionMethodCall.getMaxChanges();
    }
}
