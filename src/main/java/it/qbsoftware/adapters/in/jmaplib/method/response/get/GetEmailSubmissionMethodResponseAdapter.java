package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponsePort;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse;

public class GetEmailSubmissionMethodResponseAdapter
        implements GetEmailSubmissionMethodResponsePort {
    private GetEmailSubmissionMethodResponse getEmailSubmissionMethodResponse;

    public GetEmailSubmissionMethodResponseAdapter(
            final GetEmailSubmissionMethodResponse getEmailSubmissionMethodResponse) {
        this.getEmailSubmissionMethodResponse = getEmailSubmissionMethodResponse;
    }

    public GetEmailSubmissionMethodResponse adaptee() {
        return getEmailSubmissionMethodResponse;
    }
}
