package it.qbsoftware.core.util;

import it.qbsoftware.persistence.EmailDao;
import it.qbsoftware.persistence.EmailImp;
import it.qbsoftware.persistence.IdentityImp;
import it.qbsoftware.persistence.MailboxInfoDao;
import it.qbsoftware.persistence.MailboxInfoImp;
import it.qbsoftware.persistence.MongoConnectionSingleton;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;

import com.google.common.collect.ImmutableMap;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.Capability;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.EmailAddress;
import rs.ltt.jmap.common.entity.EmailBodyPart;
import rs.ltt.jmap.common.entity.EmailBodyValue;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Role;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;
import rs.ltt.jmap.mock.server.EmailGenerator;
import rs.ltt.jmap.mock.server.MockMailServer;

public class GenPocData {

  public static void generate() {
    MongoConnectionSingleton.INSTANCE.getConnection().getDatabase().drop();
    DELETEME deleteme = new DELETEME(0);
    generateAccount();
    generateMail();
    generateIdentity();
    generateMailboxInfo();
    EmailDao emailDao = new EmailImp();
    for (var x : deleteme.getEmails().values().toArray(new Email[0])) {
      emailDao.saveEmail(x);
       JmapSingleton.INSTANCE.getJmap().increaseState();
     }
    // generateMail();
  }

  static void generateAccount() {
    Document doc = new Document();
    doc.put("_id", "0");
    doc.put("state", "0");
    MongoConnectionSingleton.INSTANCE
        .getConnection().getDatabase().getCollection(
            "Account")
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
            .bodyStructure(EmailBodyPart.builder().partId("0").type("text/plain").build())
            .bodyValue(
                "0",
                EmailBodyValue.builder()
                    .value("Ciao,questo è un esempio di contenuto (body) di un e-mail.")
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

class DELETEME extends MockMailServer {

  public DELETEME(int numThreads) {
    super(numThreads);
  }

  public Map<String, Email> getEmails() {
    return emails;
  }

  public Map<String, MailboxInfo> getMailboxInfo() {
    return mailboxes;
  }
}
