package it.qbsoftware.adapters.jmaplib;

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
    public EmailPort[] retrive(String[] emailsIds) {
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
    

}
