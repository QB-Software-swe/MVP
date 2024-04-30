package it.qbsoftware.business.ports.in.jmap.entity;

import java.time.Instant;
import java.util.Map;

public interface EmailBuilderPort {
    public EmailBuilderPort id(final String id);

    public EmailBuilderPort blobId(final String id);

    public EmailBuilderPort threadId(final String threadId);

    public EmailBuilderPort receivedAt(final Instant instant);

    public EmailBuilderPort size(final Long size);

    public EmailBuilderPort mailboxId(final String mailboxIdKey, final Boolean mailboxIdValue);

    public EmailBuilderPort attachment(final EmailBodyPartPort emailBodyPartPort);

    public EmailBuilderPort keywords(final Map<String, Boolean> keywords);

    public EmailBuilderPort mailboxIds(final Map<String, Boolean> mailboxIds);

    public EmailBuilderPort clearMailboxIds();

    public EmailBuilderPort clearAttachments();

    public EmailPort build();

    public EmailBuilderPort reset();

    public EmailBuilderPort keywords(String string, Boolean mBoolean);
}
