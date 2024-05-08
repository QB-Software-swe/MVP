package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public interface MailboxRepository {

    public RetrivedEntity<MailboxPort> retriveAll(final String accountId);

    public RetrivedEntity<MailboxPort> retrive(final String[] ids);

    public MailboxPort retriveOne(final String id) throws SetNotFoundException;

    public void overwrite(final MailboxPort mailboxPort) throws SetNotFoundException;

    public void destroy(final String id) throws SetNotFoundException;

    public void save(final MailboxPort mailboxPort) throws SetSingletonException;
}
