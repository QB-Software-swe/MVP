package it.qbsoftware.adapters.jmaplib;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import rs.ltt.jmap.common.entity.Email;

public class EmailAdapter implements EmailPort{
    Email email;

    public EmailAdapter(Email email){
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
        return email.getAttachments().stream().map(EmailBodyPartAdapter::new).collect(Collectors.toList());
    }

    @Override
    public Map<String, EmailBodyValuePort> getBodyValues() {
        return email.getBodyValues().entrySet().stream().collect(Collectors.toMap(Entry::getKey, e-> new EmailBodyValueAdapter(e.getValue())));
    }

    @Override
    public Instant getReceivedAt() {
        return email.getReceivedAt();
    }

    @Override
    public EmailBuilderPort toBuilder() {

    }

}
