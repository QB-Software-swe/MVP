package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

public interface UpdateMailbox {
    UpdatedResult<MailboxPort> update(SetMailboxMethodCallPort setMailboxMethodCallPort);

}