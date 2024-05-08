package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailHeaderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
            this.emailBuilder.attachment(((EmailBodyPartAdapter) emailBodyPartPort).adaptee());
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

    @Override
    public EmailBuilderPort messageId(final List<String> messageIds) {
        emailBuilder.messageId(messageIds);
        return this;
    }

    @Override
    public EmailBuilderPort inReplyTo(final List<String> inReplyTo) {
        emailBuilder.inReplyTo(inReplyTo);
        return this;
    }

    @Override
    public EmailBuilderPort references(final List<String> references) {
        emailBuilder.references(references);
        return this;
    }

    @Override
    public EmailBuilderPort sender(final List<EmailAddressPort> sender) {
        emailBuilder.sender(
                sender != null
                        ? sender.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList()
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort from(final List<EmailAddressPort> from) {
        emailBuilder.from(
                from != null
                        ? from.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList()
                        : null);
        return this;
    }
    ;

    @Override
    public EmailBuilderPort to(final List<EmailAddressPort> to) {
        emailBuilder.to(
                to != null
                        ? to.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList()
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort cc(final List<EmailAddressPort> cc) {
        emailBuilder.cc(
                cc != null
                        ? cc.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList()
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort bcc(final List<EmailAddressPort> bcc) {
        emailBuilder.bcc(
                bcc != null
                        ? bcc.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList()
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort replyTo(final List<EmailAddressPort> replyTO) {
        emailBuilder.replyTo(
                replyTO != null
                        ? replyTO.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList()
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort subject(final String subject) {
        emailBuilder.subject(subject);
        return this;
    }

    @Override
    public EmailBuilderPort bodyValues(final Map<String, EmailBodyValuePort> bodyValues) {
        emailBuilder.bodyValues(
                bodyValues != null
                        ? bodyValues.entrySet().stream()
                                .collect(
                                        Collectors.toMap(
                                                e -> e.getKey(),
                                                e ->
                                                        ((EmailBodyValueAdapter) e.getValue())
                                                                .adaptee()))
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort textBody(final Collection<EmailBodyPartPort> textBody) {
        emailBuilder.textBody(
                textBody != null
                        ? textBody.stream()
                                .map(e -> ((EmailBodyPartAdapter) e).adaptee())
                                .collect(Collectors.toList())
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort htmlBody(final Collection<EmailBodyPartPort> htmlBody) {
        emailBuilder.htmlBody(
                htmlBody != null
                        ? htmlBody.stream()
                                .map(h -> ((EmailBodyPartAdapter) h).adaptee())
                                .collect(Collectors.toList())
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort attachments(final Collection<EmailBodyPartPort> attachments) {
        emailBuilder.attachments(
                attachments != null
                        ? attachments.stream()
                                .map(a -> ((EmailBodyPartAdapter) a).adaptee())
                                .collect(Collectors.toList())
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort sentAt(final OffsetDateTime sentAt) {
        emailBuilder.sentAt(sentAt);
        return this;
    }

    @Override
    public EmailBuilderPort bodyStructure(final EmailBodyPartPort emailBodyPart) {
        emailBuilder.bodyStructure(
                emailBodyPart != null ? ((EmailBodyPartAdapter) emailBodyPart).adaptee() : null);
        return this;
    }

    @Override
    public EmailBuilderPort headers(final Collection<EmailHeaderPort> headers) {
        emailBuilder.headers(
                headers != null
                        ? headers.stream()
                                .map(h -> ((EmailHeaderAdapter) h).adaptee())
                                .collect(Collectors.toList())
                        : null);
        return this;
    }

    @Override
    public EmailBuilderPort hasAttachment(final Boolean hasAttachment) {
        emailBuilder.hasAttachment(hasAttachment);
        return this;
    }
}
