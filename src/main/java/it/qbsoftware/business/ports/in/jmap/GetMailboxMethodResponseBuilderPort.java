package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public interface GetMailboxMethodResponseBuilderPort {
    public GetMailboxMethodResponseBuilderPort list(final MailboxPort[] mailboxs);

    public GetMailboxMethodResponseBuilderPort notFound(final String[] ids);

    public GetMailboxMethodResponseBuilderPort state(final String state);

    public GetMailboxMethodResponsePort build();

    public GetMailboxMethodResponseBuilderPort reset();
}
