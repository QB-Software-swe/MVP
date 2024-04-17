package it.qbsoftware.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.mongodb.client.MongoClients;

import it.qbsoftware.adapters.in.jmaplib.AccountBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.SessionResourceBuilderAdapter;
import it.qbsoftware.adapters.out.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.application.config.JmapConfig;
import it.qbsoftware.application.config.JmapEndpoint;
import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.business.services.SessionService;
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

        // Repository
        bind(UserSessionResourceRepository.class).to(UserSessionResourceRepositoryAdapter.class);

        // Service
        bind(SessionUsecase.class).to(SessionService.class);

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
}
