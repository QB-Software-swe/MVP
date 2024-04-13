package it.qbsoftware.adapters.jmaplib;

import java.time.Instant;
import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;
import rs.ltt.jmap.common.entity.Email;

public class EmailBuilderAdapter implements EmailBuilderPort{
    EmailBuilder emailBuilder;

    public EmailBuilderAdapter(){
        this.emailBuilder = Email.builder();
    }

    @Override
    public EmailBuilderPort id(String id) {
        emailBuilder.id(id);
        return this;
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
        emailBuilder.attachment(((EmailBodyPartAdapter)emailBodyPartPort).emailBodyPart);
        return this;
    }

    @Override
    public EmailBuilderPort keywords(Map<String, Boolean> keywords) {
        emailBuilder.keywords(keywords);
        return this;
    }

    @Override
    public EmailBuilderPort mailboxIds(Map<String, Boolean> mailboxIds) {
        emailBuilder.mailboxIds(mailboxIds);
        return this;
    }

    @Override
    public EmailBuilderPort clearMailboxIds() {
        emailBuilder.clearMailboxIds();
        return this;
    }

    @Override
    public EmailBuilderPort clearAttachments() {
        emailBuilder.clearAttachments();
        return this;
    }

    @Override
    public EmailPort build() {
        return new EmailAdapter(emailBuilder.build());
    }

    @Override
    public EmailBuilderPort reset() {
        this.emailBuilder = Email.builder();
        return this;
    }

    

}
