package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import rs.ltt.jmap.common.entity.EmailSubmission;
import rs.ltt.jmap.common.entity.EmailSubmission.EmailSubmissionBuilder;

public class EmailSubmissionBuilderAdapter implements EmailSubmissionBuilderPort {
    private EmailSubmissionBuilder emailSubmissionBuilder = EmailSubmission.builder();
}
