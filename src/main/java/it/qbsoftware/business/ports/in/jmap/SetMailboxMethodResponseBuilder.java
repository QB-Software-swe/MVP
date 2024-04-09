package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;

public interface SetMailboxMethodResponseBuilder {
    public SetMailboxMethodResponseBuilder created(String createdKey, MailboxPort createdMailbox);

    public SetMailboxMethodResponseBuilder notCreated(String notCreatedKey, SetErrorPort notCreatedError);

    public SetMailboxMethodResponseBuilder notUpdated(String id, SetErrorPort copySetErrorPort);
}
