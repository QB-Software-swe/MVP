package it.qbsoftware.adapters.jmaplib;

import java.time.Instant;
import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.EmailBodyPart;

public class EmailBuilderAdapter implements EmailBuilderPort{
    EmailBuilder emailBuilder;

    @Override
    public EmailBuilderPort id(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'id'");
    }

    @Override
    public EmailBuilderPort threadId(String threadId) {
        emailBuilder.threadId(threadId);
        return this;
    }

    @Override
    public EmailBuilderPort receivedAt(Instant instant) {
        emailBuilder.receivedAt(instant);
        return this;
    }

    @Override
    public EmailBuilderPort mailboxId(String mailboxIdKey, Boolean mailboxIdValue) {
        emailBuilder.mailboxId(mailboxIdKey, mailboxIdValue);
        return this;
    }

    @Override
    public EmailBuilderPort attachment(EmailBodyPartPort emailBodyPartPort) {
        emailBuilder.attachment((EmailBodyPart) emailBodyPartPort);
        return this;
    }

    @Override
    public EmailBuilderPort keywords(Map<String, Boolean> keywords) {
        
    }

    @Override
    public EmailBuilderPort mailboxIds(Map<String, Boolean> mailboxIds) {
        
    }

    @Override
    public EmailBuilderPort clearMailboxIds() {
        
    }

    @Override
    public EmailBuilderPort clearAttachments() {
        
    }

    @Override
    public EmailPort build() {
        return new EmailAdapter(emailBuilder.build());
    }

    @Override
    public EmailBuilderPort reset() {
        Email.builder();
        return this;
    }

    

}
