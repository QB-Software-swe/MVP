package it.qbsoftware.application.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.mongodb.client.MongoClients;

import it.qbsoftware.adapters.in.jmaplib.entity.AccountBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.IdentityBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ThreadBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.AccountNotFoundMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidArgumentsMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailSubmissionMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetThreadMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.util.ResultReferenceResolverAdapter;
import it.qbsoftware.adapters.out.AccountStateRepositoryAdapter;
import it.qbsoftware.adapters.out.EmailRepositoryAdapter;
import it.qbsoftware.adapters.out.EmailSubmissionRepositoryAdapter;
import it.qbsoftware.adapters.out.IdentityRepositoryAdapter;
import it.qbsoftware.adapters.out.MailboxRepositoryAdapter;
import it.qbsoftware.adapters.out.ThreadRepositoryAdapter;
import it.qbsoftware.adapters.out.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.application.config.JmapConfig;
import it.qbsoftware.application.config.JmapEndpoint;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardEmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardEmailSubmissionPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardIdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardMailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.process.get.JmapReferenceIdsResolver;
import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.business.services.SessionService;
import it.qbsoftware.business.services.get.GetEmailMethodCallService;
import it.qbsoftware.business.services.get.GetEmailSubmissionMethodCallService;
import it.qbsoftware.business.services.get.GetIdentityMethodCallService;
import it.qbsoftware.business.services.get.GetMailboxMethodCallService;
import it.qbsoftware.business.services.get.GetThreadMethodCallService;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.gson.JmapAdapters;

public class ControllerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SessionResourceBuilderPort.class).to(SessionResourceBuilderAdapter.class);
        bind(AccountBuilderPort.class).to(AccountBuilderAdapter.class);
        bind(EndPointConfiguration.class).to(JmapEndpoint.class);
        bind(CapabilityPort[].class).annotatedWith(Names.named("serverCapabilities"))
                .toInstance(JmapConfig.serverCapabilities());
        bind(Gson.class).toInstance(jampGsonConfig());

        // Adapter in JMAP
        bind(MailboxBuilderPort.class).to(MailboxBuilderAdapter.class);
        bind(GetMailboxMethodResponseBuilderPort.class).to(GetMailboxMethodResponseBuilderAdapter.class);
        bind(InvalidResultReferenceMethodErrorResponsePort.class)
                .to(InvalidResultReferenceMethodErrorResponseAdapter.class);
        bind(InvalidArgumentsMethodErrorResponsePort.class).to(InvalidArgumentsMethodErrorResponseAdapter.class);
        bind(ResultReferenceResolverPort.class).to(ResultReferenceResolverAdapter.class);
        bind(MailboxBuilderPort.class).to(MailboxBuilderAdapter.class);
        bind(AccountNotFoundMethodErrorResponsePort.class).to(AccountNotFoundMethodErrorResponseAdapter.class);

        // Adapter out JMAP ?
        bind(AccountStateRepository.class).to(AccountStateRepositoryAdapter.class);
        bind(AccountStateRepository.class).to(AccountStateRepositoryAdapter.class);
        bind(UserSessionResourceRepository.class).to(UserSessionResourceRepositoryAdapter.class);
        bind(MailboxRepository.class).to(MailboxRepositoryAdapter.class);

        // Domain?
        bind(GetReferenceIdsResolver.class)
                .toInstance(new JmapReferenceIdsResolver(new ResultReferenceResolverAdapter()));
        bind(MailboxPropertiesFilter.class)
                .toInstance(new StandardMailboxPropertiesFilter(new MailboxBuilderAdapter()));

        // Service
        bind(SessionUsecase.class).to(SessionService.class);

        bind(GetEmailMethodCallUsecase.class)
                .toInstance(
                        new GetEmailMethodCallService(gStateRepositoryAdapter(),
                                new StandardEmailPropertiesFilter(new EmailBuilderAdapter()),
                                new EmailRepositoryAdapter(),
                                new GetEmailMethodResponseBuilderAdapter(),
                                new JmapReferenceIdsResolver(new ResultReferenceResolverAdapter()),
                                new InvalidArgumentsMethodErrorResponseAdapter(),
                                new InvalidResultReferenceMethodErrorResponseAdapter(),
                                new AccountNotFoundMethodErrorResponseAdapter()));
        bind(GetIdentityMethodCallUsecase.class).toInstance(
                new GetIdentityMethodCallService(gStateRepositoryAdapter(),
                        new GetIdentityMethodResponseBuilderAdapter(),
                        new JmapReferenceIdsResolver(new ResultReferenceResolverAdapter()),
                        new StandardIdentityPropertiesFilter(new IdentityBuilderAdapter()),
                        new IdentityRepositoryAdapter(),
                        new InvalidArgumentsMethodErrorResponseAdapter(),
                        new InvalidResultReferenceMethodErrorResponseAdapter(),
                        new AccountNotFoundMethodErrorResponseAdapter()));
        bind(GetMailboxMethodCallUsecase.class).toInstance(
                new GetMailboxMethodCallService(
                        new AccountStateRepositoryAdapter(mongoConnection(), jampGsonConfig()),
                        new GetMailboxMethodResponseBuilderAdapter(),
                        new JmapReferenceIdsResolver(new ResultReferenceResolverAdapter()),
                        new InvalidArgumentsMethodErrorResponseAdapter(),
                        new InvalidResultReferenceMethodErrorResponseAdapter(),
                        new StandardMailboxPropertiesFilter(new MailboxBuilderAdapter()),
                        new MailboxRepositoryAdapter(),
                        new AccountNotFoundMethodErrorResponseAdapter()));
        bind(GetThreadMethodCallUsecase.class).toInstance(
                new GetThreadMethodCallService(gStateRepositoryAdapter(),
                        new JmapReferenceIdsResolver(new ResultReferenceResolverAdapter()),
                        new GetThreadMethodResponseBuilderAdapter(),
                        new StandardThreadPropertiesFilter(new ThreadBuilderAdapter()), new ThreadRepositoryAdapter(),
                        new InvalidArgumentsMethodErrorResponseAdapter(),
                        new InvalidResultReferenceMethodErrorResponseAdapter(),
                        new AccountNotFoundMethodErrorResponseAdapter()));
        bind(GetEmailSubmissionMethodCallUsecase.class).toInstance(
                new GetEmailSubmissionMethodCallService(gStateRepositoryAdapter(),
                        new StandardEmailSubmissionPropertiesFilter(new EmailSubmissionBuilderAdapter()),
                        new EmailSubmissionRepositoryAdapter(), new GetEmailSubmissionMethodResponseBuilderAdapter(),
                        new JmapReferenceIdsResolver(new ResultReferenceResolverAdapter()),
                        new InvalidArgumentsMethodErrorResponseAdapter(),
                        new InvalidResultReferenceMethodErrorResponseAdapter(),
                        new AccountNotFoundMethodErrorResponseAdapter()));

        // Database
        bind(MongoConnection.class)
                .toInstance(mongoConnection());
    }

    @Singleton
    private Gson jampGsonConfig() {
        Gson gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        gson = gsonBuilder.create();

        return gson;
    }

    @Singleton
    private MongoConnection mongoConnection() {
        return new MongoConnection(MongoClients.create("mongodb://rootuser:rootpass@dbhost:27017/"));
    }

    private AccountStateRepositoryAdapter gStateRepositoryAdapter() {
        return new AccountStateRepositoryAdapter(mongoConnection(), jampGsonConfig());
    }
}
