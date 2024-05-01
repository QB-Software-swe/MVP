package it.qbsoftware.business.ports.in.jmap.entity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmailBuilderPort {
    public EmailBuilderPort id(String id);

    public EmailBuilderPort blobId(String id);

    public EmailBuilderPort threadId(String threadId);

    public EmailBuilderPort receivedAt(Instant instant);

    public EmailBuilderPort size(Long size);

    public EmailBuilderPort mailboxId(String mailboxIdKey, Boolean mailboxIdValue);

    public EmailBuilderPort attachment(EmailBodyPartPort emailBodyPartPort);

    public EmailBuilderPort keywords(Map<String, Boolean> keywords);

    public EmailBuilderPort mailboxIds(Map<String, Boolean> mailboxIds);

    public EmailBuilderPort messageId(List<String> messageIds);

    public EmailBuilderPort inReplyTo(List<String> inReplyTo);

    public EmailBuilderPort references(List<String> references);

    public EmailBuilderPort sender(List<EmailAddressPort> sender);

    public EmailBuilderPort from(List<EmailAddressPort> from);

    public EmailBuilderPort to(List<EmailAddressPort> to);

    public EmailBuilderPort cc(List<EmailAddressPort> cc);

    public EmailBuilderPort bcc(List<EmailAddressPort> bcc);

    public EmailBuilderPort replyTo(List<EmailAddressPort> replyTo);

    public EmailBuilderPort subject(String subject);

    public EmailBuilderPort bodyValues(Map<String, EmailBodyPartPort> bodyValues);

    public EmailBuilderPort textBody(Collection<EmailBodyPartPort> textBody);

    public EmailBuilderPort htmlBody(Collection<EmailBodyPartPort> htmlBody);

    public EmailBuilderPort attachments(Collection<EmailBodyPartPort> attachments);

    public EmailBuilderPort sentAt(OffsetDateTime sentAt);

    public EmailBuilderPort bodyStructure(EmailBodyPartPort emailBodyPart);

    public EmailBuilderPort clearMailboxIds();

    public EmailBuilderPort clearAttachments();

    public EmailPort build();

    public EmailBuilderPort reset();

    public EmailBuilderPort keywords(String string, Boolean mBoolean);
}
