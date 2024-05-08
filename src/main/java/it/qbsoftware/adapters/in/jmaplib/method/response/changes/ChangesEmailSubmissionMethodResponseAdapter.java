package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponsePort;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse;

public class ChangesEmailSubmissionMethodResponseAdapter
        implements ChangesEmailSubmissionMethodResponsePort {
    private final ChangesEmailSubmissionMethodResponse changesEmailSubmissionMethodResponse;

    public ChangesEmailSubmissionMethodResponseAdapter(
            final ChangesEmailSubmissionMethodResponse changesEmailSubmissionMethodResponse) {
        this.changesEmailSubmissionMethodResponse = changesEmailSubmissionMethodResponse;
    }

    public ChangesEmailSubmissionMethodResponse adaptee() {
        return changesEmailSubmissionMethodResponse;
    }
}
