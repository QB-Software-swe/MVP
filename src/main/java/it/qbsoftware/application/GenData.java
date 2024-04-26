package it.qbsoftware.application;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceAdapter;
import it.qbsoftware.adapters.out.IdentityRepositoryAdapter;
import it.qbsoftware.adapters.out.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.Capability;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.EmailAddress;
import rs.ltt.jmap.common.entity.EmailBodyPart;
import rs.ltt.jmap.common.entity.EmailBodyValue;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.Role;
import rs.ltt.jmap.common.entity.Mailbox.MailboxBuilder;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;

//TODO: remove me
//NOTA: accountid == user per questo caso
public class GenData {
        private final MongoConnection mongoConnection;
        private final UserSessionResourceRepositoryAdapter userSessionResourceRepositoryAdapter;
        private final EmailRepository emailRepository;
        private final MailboxRepository mailboxRepository;
        private final AccountStateRepository accountStateRepository;
        private final IdentityRepository identityRepository;
        private final Random random = new Random();

        private final List<String> users = Arrays.asList(new String[] {
                        "team@qbsoftware.swe.it",
                        "example.email@example.net",
                        "alphabravo@delta.a3x",
        });
        private final List<MailboxBuilder> mailboxs = Arrays.asList(new MailboxBuilder[] {
                        Mailbox.builder().name("Arrivati").role(Role.INBOX),
                        Mailbox.builder().name("Roba").role(Role.ARCHIVE)
        });
        private final Long howManyEmailsPerAccount = 20L;

        @Inject
        public GenData(final UserSessionResourceRepositoryAdapter userSessionResourceRepositoryAdapter,
                        final EmailRepository emailRepository, final MongoConnection mongoConnection,
                        final AccountStateRepository accountStateRepository,
                        final IdentityRepository identityRepository, final MailboxRepository mailboxRepository) {
                this.mongoConnection = mongoConnection;
                this.userSessionResourceRepositoryAdapter = userSessionResourceRepositoryAdapter;
                this.emailRepository = emailRepository;
                this.mailboxRepository = mailboxRepository;
                this.accountStateRepository = accountStateRepository;
                this.identityRepository = identityRepository;
        }

        public void generate() {
                mongoConnection.getDatabase().drop();
                generateSession();
                generateIdentity();
                final List<String> mailboxIds = generateMailbox();
                genEmails(mailboxIds);
                genMailbox(mailboxIds);
        }

        public void genMailbox(final List<String> mailboxIds) {

                for (final String mailboxId : mailboxIds) {
                        try {
                                mailboxRepository.save(new MailboxAdapter(
                                                Mailbox.builder()
                                                                .id(mailboxId)
                                                                .name(UUID.randomUUID().toString())
                                                                .role(Role.ALL)
                                                                .build()

                                ));
                        } catch (SetSingletonException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }

        }

        public void generateIdentity() {
                for (final String user : users) {
                        for (final String userIdentity : users) {
                                try {
                                        identityRepository.save(
                                                        new IdentityAdapter(
                                                                        Identity.builder()
                                                                                        .id(user + "/" + userIdentity)
                                                                                        .email(userIdentity)
                                                                                        .name("Identità d'esempio")
                                                                                        .build()));
                                } catch (final SetSingletonException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        private List<String> generateMailbox() {
                final List<String> mailboxids = new ArrayList<>();

                for (String user : users) {
                        for (MailboxBuilder mailboxBuilder : mailboxs) {
                                final String mailboxId = user + "/" + UUID.randomUUID().toString();
                                mailboxids.add(mailboxId);
                                /*
                                 * try {
                                 * mailboxRepository .save(new
                                 * MailboxAdapter(mailboxBuilder.id(mailboxId).build()));
                                 * } catch (SetSingletonException e) {
                                 * e.printStackTrace();
                                 * }
                                 */
                        }
                }

                return mailboxids;
        }

        private void genEmails(final List<String> mailboxIds) {
                for (final String user : users) {
                        for (Long x = 0L; x < howManyEmailsPerAccount; x++) {
                                Email email = Email.builder()
                                                .id(user + "/" + UUID.randomUUID().toString())
                                                .threadId(user + "/" + UUID.randomUUID().toString())
                                                .sentAt(Instant.now().atOffset(ZoneOffset.ofHours(1)))
                                                .receivedAt(Instant.now())
                                                .to(
                                                                EmailAddress.builder()
                                                                                .email("team@qbsoftware.org")
                                                                                .name("QB Software")
                                                                                .build())
                                                .mailboxId(mailboxIds.get(random.nextInt(mailboxIds.size())), true)
                                                .from(
                                                                EmailAddress.builder()
                                                                                .email("example@mail.org")
                                                                                .name("Nome del mittente")
                                                                                .build())
                                                .subject("Esempio oggetto dell'e-mail")
                                                .preview("Una breve preview dell'e-mail")
                                                .bodyStructure(
                                                                EmailBodyPart.builder().partId("0").type("text/plain")
                                                                                .build())
                                                .bodyValue(
                                                                "0",
                                                                EmailBodyValue.builder()
                                                                                .value(
                                                                                                "Ciao, questo è un esempio di contenuto (body)"
                                                                                                                + " di un e-mail.")
                                                                                .build())
                                                .textBody(
                                                                EmailBodyPart.builder().partId("0").type("text/plain")
                                                                                .build())
                                                .keyword("$seen", true)
                                                .build();

                                try {
                                        emailRepository.save(new EmailAdapter(email));
                                } catch (SetSingletonException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        private void generateSession() {
                for (final String user : users) {
                        userSessionResourceRepositoryAdapter.save(user,
                                        new SessionResourceAdapter(new JmapSession().generateSessionResources()));

                        accountStateRepository.save(new AccountState(user));
                }
        }
}

class JmapSession {
        static final String API_ENDPOINT = "/api";
        static final String UPLOAD_ENDPOINT = "/upload";
        static final String DOWNLOAD_ENDPOINT = "/download";

        long maxSizeAttachmentsPerEmail;
        String state;

        static ImmutableMap.Builder<Class<? extends Capability>, Capability> serverCapability;

        static {
                serverCapability = ImmutableMap.builder();
                serverCapability.put(
                                CoreCapability.class,
                                CoreCapability.builder()
                                                .maxSizeUpload(50_000_000L)
                                                .maxConcurrentUpload(4L)
                                                .maxCallsInRequest(16L)
                                                .maxObjectsInGet(500L)
                                                .maxObjectsInSet(500L)
                                                .build());
                serverCapability.put(MailCapability.class, MailCapability.builder().build());
        }

        public JmapSession() {
                maxSizeAttachmentsPerEmail = 0;
                state = "0";
        }

        public SessionResource generateSessionResources() {
                SessionResourceBuilder sessionResourceBuilder = SessionResource.builder();

                sessionResourceBuilder
                                .apiUrl(API_ENDPOINT)
                                .uploadUrl(UPLOAD_ENDPOINT)
                                .downloadUrl(DOWNLOAD_ENDPOINT)
                                .eventSourceUrl("/eventsource")
                                .state(state);

                // Example
                sessionResourceBuilder
                                .username("qbsoftware")
                                .account(
                                                "0",
                                                Account.builder()
                                                                .name("QB Software")
                                                                .accountCapabilities(
                                                                                ImmutableMap.of(
                                                                                                MailAccountCapability.class,
                                                                                                MailAccountCapability
                                                                                                                .builder()
                                                                                                                .maxMailboxDepth(
                                                                                                                                100L)
                                                                                                                .maxMailboxesPerEmail(
                                                                                                                                100L)
                                                                                                                .maxSizeAttachmentsPerEmail(
                                                                                                                                10000L)
                                                                                                                .maxSizeMailboxName(
                                                                                                                                10000L)
                                                                                                                .mayCreateTopLevelMailbox(
                                                                                                                                true)
                                                                                                                .build()))
                                                                .isPersonal(true)
                                                                .isReadOnly(false)
                                                                .build())
                                .capabilities(serverCapability.build())
                                .primaryAccounts(ImmutableMap.of(MailAccountCapability.class, "0"))
                                .build();

                return sessionResourceBuilder.build();
        }
}