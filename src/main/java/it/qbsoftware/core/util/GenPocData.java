package it.qbsoftware.core.util;

import it.qbsoftware.persistence.IdentityImp;
import it.qbsoftware.persistence.MailboxInfoDao;
import it.qbsoftware.persistence.MailboxInfoImp;
import it.qbsoftware.persistence.MongoConnectionSingleton;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Role;

public class GenPocData {

    public static void generate() {
        MongoConnectionSingleton.INSTANCE.getConnection().getDatabase().drop();
        generateIdentity();
        generateMailboxInfo();
    }

    static void generateIdentity() {
        new IdentityImp()
                .saveIdentity(
                        Identity
                                .builder()
                                .id("0")
                                .email("team@qbsoftware.it")
                                .name("QB Software")
                                .build());
    }

    static void generateMailboxInfo() {
        MailboxInfo[] mailboxes = new MailboxInfo[] {
                new MailboxInfo("0", "Inbox", Role.INBOX),
                new MailboxInfo("1", "Importanti", Role.IMPORTANT),
                new MailboxInfo("2", "Inviate", Role.SENT)
        };

        MailboxInfoDao mailboxInfoDao = new MailboxInfoImp();
        for (MailboxInfo mailboxInfo : mailboxes) {
            mailboxInfoDao.saveMailboxInfo(mailboxInfo);
        }
    }
}
