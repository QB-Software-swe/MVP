package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import rs.ltt.jmap.common.entity.EmailSubmission;

public class EmailSubmissionAdapter implements EmailSubmissionPort {
    private EmailSubmission emailSubmission;

    public EmailSubmissionAdapter(final EmailSubmission emailSubmission) {
        this.emailSubmission = emailSubmission;
    }

    @Override
    public String getId() {
        return emailSubmission.getId();
    }

    public EmailSubmission adaptee() {
        return emailSubmission;
    }
}
