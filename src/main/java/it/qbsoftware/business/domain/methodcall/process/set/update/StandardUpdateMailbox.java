package it.qbsoftware.business.domain.methodcall.process.set.update;

import java.util.HashMap;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

public class StandardUpdateMailbox implements UpdateMailbox {
    // TODO
    @Override
    public UpdatedResult<MailboxPort> update(final SetMailboxMethodCallPort setMailboxMethodCallPort) {
        final HashMap<String, MailboxPort> updated = new HashMap<>();
        final HashMap<String, SetErrorPort> notUpdated = new HashMap<>();
        return new UpdatedResult<>(updated, notUpdated);
    }
}
