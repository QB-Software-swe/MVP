package it.qbsoftware.adapters.jmaplib;

import java.util.stream.Stream;

import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse.GetMailboxMethodResponseBuilder;

public class GetMailboxMethodResponseBuilderAdapter implements GetMailboxMethodResponseBuilderPort{
    GetMailboxMethodResponseBuilder getMailboxMethodResponseBuilder;

    @Override
    public GetMailboxMethodResponseBuilderPort list(MailboxPort[] mailboxs) {
        getMailboxMethodResponseBuilder.list(Stream.of(mailboxs).map(mailboxPort -> ((MailboxAdapter)mailboxPort).mailbox).toArray(Mailbox[]::new));
        return this;
    }

    @Override
    public GetMailboxMethodResponseBuilderPort state(String state) {
        getMailboxMethodResponseBuilder.state(state);
        return this;
    }

    @Override
    public GetMailboxMethodResponsePort build() {
        return new GetMailboxMethodResponseAdapter(getMailboxMethodResponseBuilder.build());
    }
    

}
