package it.qbsoftware.adapters.jmaplib;

import java.util.Optional;

import it.qbsoftware.business.domain.MailboxInfo;
import it.qbsoftware.business.ports.out.jmap.MailboxInfoRepository;

public class MailboxInfoRepositoryAdapter implements MailboxInfoRepository{
    MailboxInfoRepository mailboxInfoRepository;

    @Override
    public MailboxInfo[] retriveAll(String accountId) {
        return mailboxInfoRepository.retriveAll(accountId);
    }

    @Override
    public MailboxInfo[] retrive(String accountId, String[] mailboxIds) {
        return mailboxInfoRepository.retrive(accountId, mailboxIds);
    }

    @Override
    public Optional<MailboxInfo> retrive(String accountId, String mailboxId) {
        return mailboxInfoRepository.retrive(accountId, mailboxId);
    }

    @Override
    public void save(String accountId, MailboxInfo mailboxInfo) {
        mailboxInfoRepository.save(accountId, mailboxInfo);
    }

}
