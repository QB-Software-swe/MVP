package it.qbsoftware.adapters.in.jmaplib;

import java.time.Instant;
import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;
import rs.ltt.jmap.common.entity.Email;

public class EmailBuilderAdapter implements EmailBuilderPort {
    EmailBuilder emailBuilder;

    public EmailBuilderAdapter() {
        this.emailBuilder = Email.builder();
    }

    public EmailBuilderAdapter(final EmailBuilder emailBuilder) {
        this.emailBuilder = emailBuilder;
    }

    @Override
    public EmailBuilderPort id(String id) {
        this.emailBuilder.id(id);
        return this;
    }

    @Override
    public EmailBuilderPort threadId(String threadId) {
        this.emailBuilder.threadId(threadId);
        return this;
    }

    @Override
    public EmailBuilderPort receivedAt(Instant instant) {
        this.emailBuilder.receivedAt(instant);
        return this;
    }

    @Override
    public EmailBuilderPort mailboxId(String mailboxIdKey, Boolean mailboxIdValue) {
        this.emailBuilder.mailboxId(mailboxIdKey, mailboxIdValue);
        return this;
    }

    @Override
    public EmailBuilderPort attachment(EmailBodyPartPort emailBodyPartPort) {
        this.emailBuilder.attachment(((EmailBodyPartAdapter) emailBodyPartPort).emailBodyPart);
        return this;
    }

    @Override
    public EmailBuilderPort keywords(Map<String, Boolean> keywords) {
        this.emailBuilder.keywords(keywords);
        return this;
    }

    @Override
    public EmailBuilderPort mailboxIds(Map<String, Boolean> mailboxIds) {
        this.emailBuilder.mailboxIds(mailboxIds);
        return this;
    }

    @Override
    public EmailBuilderPort clearMailboxIds() {
        this.emailBuilder.clearMailboxIds();
        return this;
    }

    @Override
    public EmailBuilderPort clearAttachments() {
        this.emailBuilder.clearAttachments();
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

    @Override
    public EmailBuilderPort blobId(String id) {
        this.emailBuilder.blobId(id);
        return this;
    }

    @Override
    public EmailBuilderPort size(Long size) {
        this.emailBuilder.size(size);
        return this;
    }

}
