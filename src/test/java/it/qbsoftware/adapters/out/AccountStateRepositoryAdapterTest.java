package it.qbsoftware.adapters.out;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.gson.JmapAdapters;

public class AccountStateRepositoryAdapterTest {

    private AccountStateRepositoryAdapter underTest;

    @Container
    private final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @BeforeEach
    public void setUp() {

        mongoDBContainer.start();

        Gson gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        gson = gsonBuilder.create();

        underTest = new AccountStateRepositoryAdapter(
                new MongoConnection(MongoClients.create(mongoDBContainer.getConnectionString())), gson);
    }

    @Test
    void testRetrive() throws AccountNotFoundException {
        final String accountId = "0";
        final var session = SessionResource.builder().build();
        underTest.retrive(accountId);
    }

    @Test
    void testSave() {

    }
}
