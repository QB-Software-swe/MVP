package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class EmailRepositoryAdapter implements EmailRepository {

    @Override
    public RetrivedEntity<EmailPort> retriveAll(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retriveAll'");
    }

    @Override
    public RetrivedEntity<EmailPort> retrive(String[] ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public boolean destroy(String emailId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    @Override
    public boolean save(EmailPort emailPort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
