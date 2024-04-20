package it.qbsoftware.business.ports.in.jmap.method.response.set;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public interface SetMailboxMethodResponsePortBuilder {
    public SetMailboxMethodResponsePortBuilder created(String createdKey, MailboxPort createdMailbox);

    public SetMailboxMethodResponsePortBuilder notCreated(String notCreatedKey, SetErrorPort notCreatedError);

    public SetMailboxMethodResponsePortBuilder notUpdated(String id, SetErrorPort copySetErrorPort);

    public SetMailboxMethodResponsePort build();
}
