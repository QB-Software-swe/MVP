package it.qbsoftware.business.ports.in.jmap.entity;

import java.util.List;
import java.util.Map;

public interface EmailPort {
    public Map<String, Boolean> getMailboxIds();

    public List<EmailBodyPartPort> getAttachments();

    public Map<String, EmailBodyValuePort> getBodyValues();

    public EmailBuilderPort toBuilder();
}
