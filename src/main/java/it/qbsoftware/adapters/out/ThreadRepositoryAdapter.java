package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;

public class ThreadRepositoryAdapter implements ThreadRepository {

    @Override
    public RetrivedEntity<ThreadPort> retriveAll(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retriveAll'");
    }

    @Override
    public RetrivedEntity<ThreadPort> retrive(String[] ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

}
