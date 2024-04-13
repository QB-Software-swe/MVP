package it.qbsoftware.adapters.jmaplib;

import java.util.Optional;

import it.qbsoftware.business.domain.MailboxInfo;
import it.qbsoftware.business.ports.out.jmap.MailboxInfoRepository;

public class MailboxInfoRepositoryAdapter implements MailboxInfoRepository{
    MailboxInfo mailboxInfo;

    @Override
    public MailboxInfo[] retriveAll(String accountId) {
        //TODODB
        return null;
    }

    @Override
    public MailboxInfo[] retrive(String accountId, String[] mailboxIds) {
        //TODODB
        return null;
    }

    @Override
    public Optional<MailboxInfo> retrive(String accountId, String mailboxId) {
        //TODODB
        return null;
    }

    @Override
    public void save(String accountId, MailboxInfo mailboxInfo) {
        //TODODB        
    }

}
