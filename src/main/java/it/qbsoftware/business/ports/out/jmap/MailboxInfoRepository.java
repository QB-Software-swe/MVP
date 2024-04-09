package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.MailboxInfo;

public interface MailboxInfoRepository {
    public MailboxInfo[] retrive(String accountId);
}
