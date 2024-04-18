package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.adapters.SetMailboxMethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePortBuilder;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

public class SetMailboxMethodResponseBuilderAdapter implements SetMailboxMethodResponsePortBuilder {
    SetMailboxMethodResponse.SetMailboxMethodResponseBuilder setMailboxMethodResponseBuilder;

    public SetMailboxMethodResponseBuilderAdapter() {
        this.setMailboxMethodResponseBuilder = SetMailboxMethodResponse.builder();
    }

    @Override
    public SetMailboxMethodResponsePortBuilder created(String createdKey, MailboxPort createdMailbox) {
        setMailboxMethodResponseBuilder.created(createdKey, ((MailboxAdapter) createdMailbox).adaptee());
        return this;
    }

    @Override
    public SetMailboxMethodResponsePortBuilder notCreated(String notCreatedKey, SetErrorPort notCreatedError) {
        setMailboxMethodResponseBuilder.notCreated(notCreatedKey, ((SetErrorAdapter) notCreatedError).setError());
        return this;
    }

    @Override
    public SetMailboxMethodResponsePortBuilder notUpdated(final String id, final SetErrorPort copySetErrorPort) {
        setMailboxMethodResponseBuilder.notUpdated(id, ((SetErrorAdapter) copySetErrorPort).setError());
        return this;
    }

    @Override
    public SetMailboxMethodResponsePort build() {
        return new SetMailboxMethodResponseAdapter(setMailboxMethodResponseBuilder.build());
    }
}
