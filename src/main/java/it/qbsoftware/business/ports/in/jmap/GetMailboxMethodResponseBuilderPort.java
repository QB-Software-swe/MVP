package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public interface GetMailboxMethodResponseBuilderPort {
    public GetMailboxMethodResponseBuilderPort list(MailboxPort[] mailboxs);

    public GetMailboxMethodResponseBuilderPort state(final String state);

    public GetMailboxMethodResponsePort build();
}
