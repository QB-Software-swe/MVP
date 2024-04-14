package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;

public interface SetMailboxMethodResponsePortBuilder {
    public SetMailboxMethodResponsePortBuilder created(String createdKey, MailboxPort createdMailbox);

    public SetMailboxMethodResponsePortBuilder notCreated(String notCreatedKey, SetErrorPort notCreatedError);

    public SetMailboxMethodResponsePortBuilder notUpdated(String id, SetErrorPort copySetErrorPort);

    public SetMailboxMethodResponsePort build();
}
