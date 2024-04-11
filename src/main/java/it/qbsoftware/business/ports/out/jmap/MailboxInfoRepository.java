package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.MailboxInfo;

import java.util.Optional;

public interface MailboxInfoRepository {
    public MailboxInfo[] retrive(String accountId);

    public Optional<MailboxInfo> retrive(String accountId, String mailboxId);

    public void save(String accountId, MailboxInfo mailboxInfo);
}
