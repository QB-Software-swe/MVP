package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;

public class EmailSubmissionRepositoryAdapter implements EmailSubmissionRepository {

    @Override
    public RetrivedEntity<EmailSubmissionPort> retriveAll(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retriveAll'");
    }

    @Override
    public RetrivedEntity<EmailSubmissionPort> retrive(String[] ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public boolean destroy(String emailId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    @Override
    public boolean save(EmailSubmissionPort emailPort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
