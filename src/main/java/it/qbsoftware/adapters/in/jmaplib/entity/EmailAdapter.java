package it.qbsoftware.adapters.in.jmaplib.entity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailHeaderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import rs.ltt.jmap.common.entity.Email;

public class EmailAdapter implements EmailPort {
    private Email email;

    public EmailAdapter(final Email email) {
        this.email = email;
    }

    @Override
    public String getId() {
        return email.getId();
    }

    @Override
    public Map<String, Boolean> getKeywords() {
        return email.getKeywords();
    }

    @Override
    public Map<String, Boolean> getMailboxIds() {
        return email.getMailboxIds();
    }

    @Override
    public String getThreadId() {
        return email.getThreadId();
    }

    @Override
    public List<EmailBodyPartPort> getAttachments() {
        return email.getAttachments() != null
                ? email.getAttachments().stream().map(EmailBodyPartAdapter::new).collect(Collectors.toList())
                : null;
    }

    @Override
    public Map<String, EmailBodyValuePort> getBodyValues() {
        return email.getBodyValues() != null ? email.getBodyValues().entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, e -> new EmailBodyValueAdapter(e.getValue()))) : null;
    }

    @Override
    public Instant getReceivedAt() {
        return email.getReceivedAt();
    }

    @Override
    public EmailBuilderPort toBuilder() {
        return new EmailBuilderAdapter(email.toBuilder());
    }

    @Override
    public String getBlobId() {
        return this.email.getBlobId();
    }

    @Override
    public Long getSize() {
        return this.email.getSize();
    }

    public Email adaptee() {
        return this.email;
    }

    @Override
    public String getSubject() {
        return email.getSubject();
    }

    @Override
    public List<String> getMessageId() {
        return email.getMessageId();
    }

    @Override
    public List<String> getInReplyTo() {
        return email.getInReplyTo();
    }

    @Override
    public List<String> getReferences() {
        return email.getReferences();
    }

    @Override
    public List<EmailAddressPort> getSender() {
        return email.getSender() != null
                ? email.getSender().stream().map(e -> new EmailAddressAdapter(e)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailAddressPort> getFrom() {
        return email.getFrom() != null
                ? email.getFrom().stream().map(e -> new EmailAddressAdapter(e)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailAddressPort> getTo() {
        return email.getTo() != null
                ? email.getTo().stream().map(e -> new EmailAddressAdapter(e)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailAddressPort> getCc() {
        return email.getCc() != null
                ? email.getCc().stream().map(e -> new EmailAddressAdapter(e)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailAddressPort> getBcc() {
        return email.getBcc() != null
                ? email.getBcc().stream().map(e -> new EmailAddressAdapter(e)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailBodyPartPort> getTextBody() {
        return email.getTextBody() != null
                ? email.getTextBody().stream().map(t -> new EmailBodyPartAdapter(t)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailBodyPartPort> getHtmlBody() {
        return email.getHtmlBody() != null
                ? email.getHtmlBody().stream().map(t -> new EmailBodyPartAdapter(t)).collect(Collectors.toList())
                : null;
    }

    @Override
    public OffsetDateTime getSentAt() {
        return email.getSentAt();
    }

    @Override
    public EmailBodyPartPort getBodyStructure() {
        return email.getBodyStructure() != null ? new EmailBodyPartAdapter(email.getBodyStructure()) : null;
    }

    @Override
    public List<EmailHeaderPort> getHeaders() {
        return email.getHeaders() != null
                ? email.getHeaders().stream().map(h -> new EmailHeaderAdapter(h)).collect(Collectors.toList())
                : null;
    }

    @Override
    public List<EmailAddressPort> getReplyTo() {
        return email.getReplyTo() != null
                ? email.getReplyTo().stream().map(e -> new EmailAddressAdapter(e)).collect(Collectors.toList())
                : null;
    }
}
