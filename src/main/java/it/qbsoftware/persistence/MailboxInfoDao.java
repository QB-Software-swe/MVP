package it.qbsoftware.persistence;

import java.util.ArrayList;
import it.qbsoftware.core.util.MailboxInfo;
import java.util.Optional;

public interface MailboxInfoDao {
        public ArrayList<MailboxInfo> getMailboxsInfo();

        Optional<MailboxInfo> getMailboxInfo(String id);

        void saveMailboxInfo(MailboxInfo mailboxInfo);
}
