package it.qbsoftware.business.ports.in.jmap.entity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public interface EmailPort {
    public String getId();

    public String getBlobId();

    public Map<String, Boolean> getKeywords();

    public Map<String, Boolean> getMailboxIds();

    public String getThreadId();

    public Long getSize();

    public List<EmailBodyPartPort> getAttachments();

    public Map<String, EmailBodyValuePort> getBodyValues();

    public Instant getReceivedAt();

    public String getSubject();

    public List<String> getMessageId();

    public List<String> getInReplyTo();

    public List<String> getReferences();

    public List<EmailAddressPort> getSender();

    public List<EmailAddressPort> getFrom();

    public List<EmailAddressPort> getTo();

    public List<EmailAddressPort> getCc();

    public List<EmailAddressPort> getBcc();

    public List<EmailBodyPartPort> getTextBody();

    public List<EmailBodyPartPort> getHtmlBody();

    public OffsetDateTime getSentAt();

    public EmailBodyPartPort getBodyStructure();

    public List<EmailHeaderPort> getHeaders();

    public EmailBuilderPort toBuilder();
}
