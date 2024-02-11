package it.qbsoftware.core.util;

import it.qbsoftware.persistence.EmailDao;
import it.qbsoftware.persistence.EmailImp;
import it.qbsoftware.persistence.IdentityImp;
import it.qbsoftware.persistence.MailboxInfoDao;
import it.qbsoftware.persistence.MailboxInfoImp;
import it.qbsoftware.persistence.MongoConnectionSingleton;
import java.time.Instant;
import java.time.ZoneOffset;
import org.bson.Document;

import java.util.HashMap;

import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.EmailAddress;
import rs.ltt.jmap.common.entity.EmailBodyPart;
import rs.ltt.jmap.common.entity.EmailBodyValue;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Role;

public class GenPocData {

  public static void generate() {
    MongoConnectionSingleton.INSTANCE.getConnection().getDatabase().drop();
    generateAccount();
    generateMail();
    generateIdentity();
    generateMailboxInfo();
  }

  static void generateAccount() {
    Document doc = new Document();
    doc.put("_id", "0");
    doc.put("state", "0");
    MongoConnectionSingleton.INSTANCE
        .getConnection()
        .getDatabase()
        .getCollection("Account")
        .insertOne(doc);
  }

  static void generateIdentity() {
    new IdentityImp()
        .saveIdentity(
            Identity.builder().id("0").email("team@qbsoftware.it").name("QB Software").build());
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

    HashMap<String, Boolean> mx = new HashMap<String, Boolean>();
    mx.put("0", true);
    mx.put("1",true);


    Email[] emails = new Email[] {
        Email.builder()
            .id("0")
            .threadId("0")
            .sentAt(Instant.now().atOffset(ZoneOffset.ofHours(1)))
            .receivedAt(Instant.now())
            .to(EmailAddress.builder()
                .email("team@qbsoftware.org")
                .name("QB Software")
                .build())
            .mailboxId("0", true)
            .from(
                EmailAddress.builder()
                    .email("example@mail.org")
                    .name("Nome del mittente")
                    .build())
            .subject("Esempio oggetto dell'e-mail")
            .preview("Una breve preview dell'e-mail")
            .bodyStructure(EmailBodyPart.builder().partId("0").type("text/plain").build())
            .bodyValue(
                "0",
                EmailBodyValue.builder()
                    .value("Ciao,questo è un esempio di contenuto (body) di un e-mail.")
                    .build())
            .textBody(EmailBodyPart.builder().partId("0").type("text/plain").build())
            .keyword("a", false)
            .build(),

        Email.builder()
            .id("1")
            .threadId("1")
            .sentAt(Instant.now().atOffset(ZoneOffset.ofHours(1)))
            .receivedAt(Instant.now())
            .mailboxIds(mx)
            .from(
                EmailAddress.builder()
                    .email("team@qbsoftware.org")
                    .name("QB Software Team")
                    .build())
            .to(EmailAddress.builder()
                .email("team@qbsoftware.org")
                .name("QB Software Team")
                .build())
            .subject("Candidatura per la revisione RTB")
            .preview("Candidatura RTB")
            .bodyStructure(EmailBodyPart.builder().partId("0").type("text/plain").build())
            .bodyValue(
                "0",
                EmailBodyValue.builder()
                    .value("Gentile prof. X.")
                    .build())
            .textBody(EmailBodyPart.builder().partId("0").type("text/plain").build())
            .keyword("a", false)
            .build()
    };

    EmailDao emailDao = new EmailImp();

    for (Email email : emails) {
      emailDao.saveEmail(email);
    }
  }
}