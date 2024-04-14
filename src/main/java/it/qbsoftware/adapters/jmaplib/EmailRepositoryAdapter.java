package it.qbsoftware.adapters.jmaplib;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class EmailRepositoryAdapter implements EmailRepository{

    //TODO DB
    //Email email;

    @Override
    public EmailPort[] retriveAll(String accountId) {
        //TODO DB
        return null;
    }

    @Override
    public boolean destroy(String emailId) {
        //TODO DB
        return false;
    }

    @Override
    public boolean save(EmailPort emailPort) {
        //TODO DB
        return false;
    }

    @Override
    public Map<String, EmailPort> retrive(String[] id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }
    

}
