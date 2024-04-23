package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import rs.ltt.jmap.common.entity.EmailSubmission;


//TODO: FORSE È DA SISTEMARE 
public class EmailSubmissionAdapter implements EmailSubmissionPort{
    private EmailSubmission emailSubmission;

    public EmailSubmissionAdapter(EmailSubmission emailSubmission) {
        this.emailSubmission = emailSubmission;
    }
}
