package it.qbsoftware.business.ports.in.jmap.entity;

import java.time.Instant;

public interface EmailBuilderPort {
    public EmailBuilderPort id(final String id);

    public EmailBuilderPort threadId(final String threadId);

    public EmailBuilderPort receivedAt(final Instant instant);

    public EmailBuilderPort mailboxId(final String mailboxIdKey, final Boolean mailboxIdValue);

    public EmailBuilderPort attachment(final EmailBodyPartPort emailBodyPartPort);

    public EmailBuilderPort clearMailboxIds();

    public EmailBuilderPort clearAttachments();

    public EmailPort build();
}
