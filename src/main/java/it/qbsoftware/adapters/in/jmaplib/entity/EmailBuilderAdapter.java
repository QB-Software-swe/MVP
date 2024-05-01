package it.qbsoftware.adapters.in.jmaplib.entity;

import java.time.Instant;
import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;

public class EmailBuilderAdapter implements EmailBuilderPort {
    private EmailBuilder emailBuilder;

    public EmailBuilderAdapter() {
        this.emailBuilder = Email.builder();
    }

    public EmailBuilderAdapter(final EmailBuilder emailBuilder) {
        this.emailBuilder = emailBuilder;
    }

    @Override
    public EmailBuilderPort id(final String id) {
        this.emailBuilder.id(id);
        return this;
    }

    @Override
    public EmailBuilderPort threadId(final String threadId) {
        this.emailBuilder.threadId(threadId);
        return this;
    }

    @Override
    public EmailBuilderPort receivedAt(final Instant instant) {
        this.emailBuilder.receivedAt(instant);
        return this;
    }

    @Override
    public EmailBuilderPort mailboxId(final String mailboxIdKey, final Boolean mailboxIdValue) {
        this.emailBuilder.mailboxId(mailboxIdKey, mailboxIdValue);
        return this;
    }

    @Override
    public EmailBuilderPort attachment(final EmailBodyPartPort emailBodyPartPort) {
        if (emailBodyPartPort != null) {
            this.emailBuilder.attachment(((EmailBodyPartAdapter) emailBodyPartPort).emailBodyPart);
        } else {
            this.emailBuilder.attachment(null);
        }
        return this;
    }

    @Override
    public EmailBuilderPort keywords(final Map<String, Boolean> keywords) {
        if (keywords == null) {
            return this;
        }

        this.emailBuilder.keywords(keywords);
        return this;
    }

    @Override
    public EmailBuilderPort mailboxIds(final Map<String, Boolean> mailboxIds) {
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

    @Override
    public EmailBuilderPort keywords(final String string, final Boolean mBoolean) {
        this.emailBuilder.keyword(string, mBoolean);
        return this;
    }

}
