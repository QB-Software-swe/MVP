package it.qbsoftware.core.util;

import java.time.Instant;
import java.time.ZoneOffset;

import it.qbsoftware.persistence.EmailDao;
import it.qbsoftware.persistence.EmailImp;
import it.qbsoftware.persistence.IdentityImp;
import it.qbsoftware.persistence.MailboxInfoDao;
import it.qbsoftware.persistence.MailboxInfoImp;
import it.qbsoftware.persistence.MongoConnectionSingleton;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.EmailAddress;
import rs.ltt.jmap.common.entity.EmailBodyValue;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Role;

public class GenPocData {

    public static void generate() {
        MongoConnectionSingleton.INSTANCE.getConnection().getDatabase().drop();
        generateIdentity();
        generateMailboxInfo();
        generateMail();
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

    static void generateMail() {
        Email[] emails = new Email[] {
                Email.builder()
                        .id("0")
                        .threadId("0")
                        .sentAt(Instant.now().atOffset(ZoneOffset.ofHours(1)))
                        .receivedAt(Instant.now())
                        .mailboxId("0", true)
                        .from(
                                EmailAddress.builder()
                                        .email("example@mail.org")
                                        .name("Nome del mittente")
                                        .build())
                        .subject("Esempio oggetto dell'e-mail")
                        .preview("Una breve preview dell'e-mail")
                        .bodyValue("0",
                                EmailBodyValue.builder()
                                        .value("Ciao,\nquesto è un esempio di contenuto (body) di un e-mail.")
                                        .build())
                        .build()
        };

        EmailDao emailDao = new EmailImp();
        for (Email email : emails) {
            emailDao.saveEmail(email);
        }
    }
}
