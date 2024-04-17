package it.qbsoftware.adapters.in.jmaplib;

import java.util.stream.Stream;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse.GetMailboxMethodResponseBuilder;

public class GetMailboxMethodResponseBuilderAdapter implements GetMailboxMethodResponseBuilderPort {
    GetMailboxMethodResponseBuilder getMailboxMethodResponseBuilder;

    public GetMailboxMethodResponseBuilderAdapter() {
        this.getMailboxMethodResponseBuilder = GetMailboxMethodResponse.builder();
    }

    @Override
    public GetMailboxMethodResponseBuilderPort list(MailboxPort[] mailboxs) {
        getMailboxMethodResponseBuilder.list(
                Stream.of(mailboxs).map(mailboxPort -> ((MailboxAdapter) mailboxPort).mailbox).toArray(Mailbox[]::new));
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

    @Override
    public GetMailboxMethodResponseBuilderPort notFound(String[] ids) {
        getMailboxMethodResponseBuilder.notFound(ids);
        return this;
    }

    @Override
    public GetMailboxMethodResponseBuilderPort reset() {
        this.getMailboxMethodResponseBuilder = GetMailboxMethodResponse.builder();
        return this;
    }

}
