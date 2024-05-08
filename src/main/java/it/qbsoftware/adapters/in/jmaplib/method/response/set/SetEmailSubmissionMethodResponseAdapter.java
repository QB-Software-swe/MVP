package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;
import rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse;

public class SetEmailSubmissionMethodResponseAdapter
        implements SetEmailSubmissionMethodResponsePort {
    private final SetEmailSubmissionMethodResponse setEmailSubmissionMethodResponse;

    public SetEmailSubmissionMethodResponseAdapter(
            final SetEmailSubmissionMethodResponse setEmailSubmissionMethodResponse) {
        this.setEmailSubmissionMethodResponse = setEmailSubmissionMethodResponse;
    }

    public SetEmailSubmissionMethodResponse adaptee() {
        return setEmailSubmissionMethodResponse;
    }
}
