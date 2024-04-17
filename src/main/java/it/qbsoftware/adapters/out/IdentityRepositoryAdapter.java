package it.qbsoftware.adapters.out;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import rs.ltt.jmap.common.entity.Identity;

public class IdentityRepositoryAdapter implements IdentityRepository{
    Identity identity;

    //TODO COSTRUTTORE

    @Override
    public IdentityPort[] retriveAll(String accountId) {
        //TODO DB
        return null;
    }

    @Override
    public Map<String, IdentityPort> retrive(String[] threadIds) {
        //TODO DB
        return null;
    }
  

}
