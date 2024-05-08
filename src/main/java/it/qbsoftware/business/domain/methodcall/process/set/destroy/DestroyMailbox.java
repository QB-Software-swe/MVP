package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

public interface DestroyMailbox {

    DestroyedResult destroy(SetMailboxMethodCallPort setMailboxMethodCallPort)
            throws AccountNotFoundException;
}
