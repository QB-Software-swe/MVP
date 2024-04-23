package it.qbsoftware.business.domain.methodcall.process.set.create;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

public interface CreateMailbox {

    CreatedResult<MailboxPort> create(SetMailboxMethodCallPort setMailboxMethodCallPort)
            throws AccountNotFoundException;

}