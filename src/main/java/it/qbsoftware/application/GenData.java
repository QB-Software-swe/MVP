package it.qbsoftware.application;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceAdapter;
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
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.Role;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;

public class GenData {
        private final MongoConnection mongoConnection;
        private final UserSessionResourceRepositoryAdapter userSessionResourceRepositoryAdapter;
        private final MailboxRepository mailboxRepository;
        private final AccountStateRepository accountStateRepository;
        private final IdentityRepository identityRepository;

        private final List<String> users = Arrays.asList(new String[] {
                        "team@qbsoftware.swe.it",
                        "example.email@example.net",
                        "alphabravo@delta.a3x",
        });

        @Inject
        public GenData(final UserSessionResourceRepositoryAdapter userSessionResourceRepositoryAdapter,
                        final EmailRepository emailRepository, final MongoConnection mongoConnection,
                        final AccountStateRepository accountStateRepository,
                        final IdentityRepository identityRepository, final MailboxRepository mailboxRepository) {
                this.mongoConnection = mongoConnection;
                this.userSessionResourceRepositoryAdapter = userSessionResourceRepositoryAdapter;
                this.mailboxRepository = mailboxRepository;
                this.accountStateRepository = accountStateRepository;
                this.identityRepository = identityRepository;
        }

        public void generate() {
                mongoConnection.getDatabase().drop();
                generateSession();
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

        private void generateSession() {
                for (final String user : users) {
                        userSessionResourceRepositoryAdapter.save(user,
                                        new SessionResourceAdapter(new JmapSession().generateSessionResources(user)));

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

        public SessionResource generateSessionResources(final String user) {
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
                                                user,
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
                                .primaryAccounts(ImmutableMap.of(MailAccountCapability.class, user))
                                .build();

                return sessionResourceBuilder.build();
        }
}