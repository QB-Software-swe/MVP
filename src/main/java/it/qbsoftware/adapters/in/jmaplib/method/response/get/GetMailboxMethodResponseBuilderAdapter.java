package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import java.util.stream.Stream;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse.GetMailboxMethodResponseBuilder;

public class GetMailboxMethodResponseBuilderAdapter implements GetMailboxMethodResponseBuilderPort {
    private GetMailboxMethodResponseBuilder getMailboxMethodResponseBuilder;

    @Inject
    public GetMailboxMethodResponseBuilderAdapter() {
        this.getMailboxMethodResponseBuilder = GetMailboxMethodResponse.builder();
    }

    @Override
    public GetMailboxMethodResponseBuilderPort list(final MailboxPort[] mailboxs) {
        if (mailboxs != null) {
            getMailboxMethodResponseBuilder.list(
                    Stream.of(mailboxs)
                            .map(mailboxPort -> ((MailboxAdapter) mailboxPort).adaptee())
                            .toArray(Mailbox[]::new));
        } else {
            getMailboxMethodResponseBuilder.list(null);
        }
        return this;
    }

    @Override
    public GetMailboxMethodResponseBuilderPort state(final String state) {
        getMailboxMethodResponseBuilder.state(state);
        return this;
    }

    @Override
    public GetMailboxMethodResponsePort build() {
        return new GetMailboxMethodResponseAdapter(getMailboxMethodResponseBuilder.build());
    }

    @Override
    public GetMailboxMethodResponseBuilderPort notFound(final String[] ids) {
        getMailboxMethodResponseBuilder.notFound(ids);
        return this;
    }

    @Override
    public GetMailboxMethodResponseBuilderPort reset() {
        this.getMailboxMethodResponseBuilder = GetMailboxMethodResponse.builder();
        return this;
    }
}
