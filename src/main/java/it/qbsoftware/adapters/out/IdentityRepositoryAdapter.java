package it.qbsoftware.adapters.out;

import java.util.Map;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import rs.ltt.jmap.common.entity.Identity;

public class IdentityRepositoryAdapter implements IdentityRepository {

    @Override
    public RetrivedEntity<IdentityPort> retriveAll(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retriveAll'");
    }

    @Override
    public RetrivedEntity<IdentityPort> retrive(String[] ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public boolean destroy(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    @Override
    public boolean save(IdentityPort identityPort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
