package it.qbsoftware.application.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.client.MongoClients;

import it.qbsoftware.adapters.out.AccountStateRepositoryAdapter;
import it.qbsoftware.adapters.out.EmailChangesTrackerRepositoryAdapter;
import it.qbsoftware.adapters.out.EmailRepositoryAdapter;
import it.qbsoftware.adapters.out.EmailSubmissionChangesTrackerRepositoryAdapter;
import it.qbsoftware.adapters.out.EmailSubmissionRepositoryAdapter;
import it.qbsoftware.adapters.out.IdentityRepositoryAdapter;
import it.qbsoftware.adapters.out.MailboxChangesTrackerRepositoryAdapter;
import it.qbsoftware.adapters.out.MailboxRepositoryAdapter;
import it.qbsoftware.adapters.out.ThreadChangesTrackerRepositoryAdapter;
import it.qbsoftware.adapters.out.ThreadRepositoryAdapter;
import it.qbsoftware.adapters.out.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailSubmissionChangesTracker;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.EmailSubmissionChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.persistance.MongoConnection;

public class MongoRepositoryAdapterModule extends AbstractModule {
    @Override
    protected void configure() {
        // Repository Adapter
        bind(UserSessionResourceRepository.class).to(UserSessionResourceRepositoryAdapter.class);
        bind(AccountStateRepository.class).to(AccountStateRepositoryAdapter.class);
        bind(EmailRepository.class).to(EmailRepositoryAdapter.class);
        bind(EmailChangesTrackerRepository.class).to(EmailChangesTrackerRepositoryAdapter.class);
        bind(ThreadChangesTrackerRepository.class).to(ThreadChangesTrackerRepositoryAdapter.class);
        bind(MailboxChangesTrackerRepository.class).to(MailboxChangesTrackerRepositoryAdapter.class);
        bind(IdentityRepository.class).to(IdentityRepositoryAdapter.class);
        bind(MailboxRepository.class).to(MailboxRepositoryAdapter.class);
        bind(ThreadRepository.class).to(ThreadRepositoryAdapter.class);
        bind(EmailSubmissionRepository.class).to(EmailSubmissionRepositoryAdapter.class);
        bind(EmailSubmissionChangesTrackerRepository.class).to(EmailSubmissionChangesTrackerRepositoryAdapter.class);
    }

    @Singleton
    @Provides
    static MongoConnection mongoConnection() {
        return new MongoConnection(MongoClients.create("mongodb://rootuser:rootpass@localhost:27017/")); // TODO: envs
    }
}
