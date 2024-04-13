package it.qbsoftware.adapters.jmaplib;

import java.util.Optional;

import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;

public class UserSessionResourceRepositoryAdapter implements UserSessionResourceRepository{
    //TODO: IMPLEMENTARE CLASSE
    //SessionResource userSessionResource;

    @Override
    public Optional<SessionResourcePort> retrieve(String username) {
        //TODODB
        return null;
    }

    @Override
    public void save(SessionResourcePort sessionResourcePort) {
        //TODODB
    }

    


}
