package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class MailboxRepositoryAdapter implements MailboxRepository {

    @Override
    public RetrivedEntity<MailboxPort> retriveAll(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retriveAll'");
    }

    @Override
    public RetrivedEntity<MailboxPort> retrive(String[] ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public boolean destroy(String id) throws SetNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    @Override
    public boolean save(MailboxPort mailboxPort) throws SetSingletonException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
