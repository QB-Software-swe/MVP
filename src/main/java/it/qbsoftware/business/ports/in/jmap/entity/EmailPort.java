package it.qbsoftware.business.ports.in.jmap.entity;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface EmailPort {
    public String getId();

    public Map<String, Boolean> getKeywords();

    public Map<String, Boolean> getMailboxIds();

    public String getThreadId();

    public List<EmailBodyPartPort> getAttachments();

    public Map<String, EmailBodyValuePort> getBodyValues();

    public Instant getReceivedAt();

    public EmailBuilderPort toBuilder();
}
