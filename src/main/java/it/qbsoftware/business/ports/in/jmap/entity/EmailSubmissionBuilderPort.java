package it.qbsoftware.business.ports.in.jmap.entity;

import java.time.Instant;
import java.util.Map;

public interface EmailSubmissionBuilderPort {
    public EmailSubmissionBuilderPort id(String id);

    public EmailSubmissionBuilderPort emailId(String emailId);

    public EmailSubmissionBuilderPort threadId(String threadId);

    public EmailSubmissionBuilderPort sentAt(Instant instant);

    public EmailSubmissionBuilderPort deliveryStatus(Map<String, String> deliveryStatus);

    public EmailSubmissionPort build();

    public EmailSubmissionPort build1();

    public EmailSubmissionBuilderPort reset();
}
