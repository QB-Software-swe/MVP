package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

public class StandardUpdateMailbox implements UpdateMailbox {
    // TODO
    @Override
    public UpdatedResult<MailboxPort> update(final SetMailboxMethodCallPort setMailboxMethodCallPort) {
        return new UpdatedResult<>(null, null);
    }
}
