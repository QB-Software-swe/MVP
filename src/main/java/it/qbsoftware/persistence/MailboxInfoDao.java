package it.qbsoftware.persistence;

import it.qbsoftware.core.util.MailboxInfo;
import java.util.ArrayList;
import java.util.Optional;

public interface MailboxInfoDao {
  public ArrayList<MailboxInfo> getMailboxsInfo();

  Optional<MailboxInfo> getMailboxInfo(String id);

  void saveMailboxInfo(MailboxInfo mailboxInfo);
}
