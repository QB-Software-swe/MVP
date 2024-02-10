package it.qbsoftware.persistence;

import it.qbsoftware.core.util.MailboxInfo;
import java.util.List;

public interface MailboxInfoDao {
        List<MailboxInfo> getAllMailboxesInfo();
        MailboxInfo getMailboxInfo(String id);
        void saveMailboxInfo(MailboxInfo mailboxInfo);
        void deleteMailboxInfo(MailboxInfo mailboxInfo);
}
