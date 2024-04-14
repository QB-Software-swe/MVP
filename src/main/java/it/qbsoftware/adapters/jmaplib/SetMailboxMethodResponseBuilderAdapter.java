package it.qbsoftware.adapters.jmaplib;

import org.checkerframework.checker.units.qual.A;

import it.qbsoftware.business.ports.in.jmap.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.SetMailboxMethodResponseBuilder;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

public class SetMailboxMethodResponseBuilderAdapter implements SetMailboxMethodResponseBuilder{
    SetMailboxMethodResponseBuilder setMailboxMethodResponseBuilder;

    public SetMailboxMethodResponseBuilderAdapter(){
        this.setMailboxMethodResponseBuilder = SetMailboxMethodResponse.builder();
    }

    @Override
    public SetMailboxMethodResponseBuilder created(String createdKey, MailboxPort createdMailbox) {
        setMailboxMethodResponseBuilder.created(createdKey, createdMailbox);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilder notCreated(String notCreatedKey, SetErrorPort notCreatedError) {
        setMailboxMethodResponseBuilder.notCreated(notCreatedKey, notCreatedError);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilder notUpdated(String id, SetErrorPort copySetErrorPort) {
        setMailboxMethodResponseBuilder.notUpdated(id, copySetErrorPort);
        return this;
    }

    @Override
    public SetMailboxMethodResponsePort build() {
        return new SetMailboxMethodResponseAdapter(setMailboxMethodResponseBuilder.build());
    }
}
