package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class EmailRepositoryAdapter implements EmailRepository{

    //TODODB
    //Email email;

    @Override
    public EmailPort[] retriveAll(String accountId) {
        //TODODB
        return null;
    }

    @Override
    public EmailPort[] retrive(String[] emailsIds) {
        //TODODB
        return null;
    }

    @Override
    public boolean destroy(String emailId) {
        //TODODB
        return false;
    }

    @Override
    public boolean save(EmailPort emailPort) {
        //TODODB
        return false;
    }
    

}
