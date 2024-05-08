package it.qbsoftware.adapters.out;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.persistance.MongoConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import rs.ltt.jmap.gson.JmapAdapters;

public class AccountStateRepositoryAdapterTest {

    private AccountStateRepositoryAdapter underTest;

    @Container
    private final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    private Gson gson;

    @BeforeEach
    public void setUp() {

        mongoDBContainer.start();

        gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        gson = gsonBuilder.create();

        underTest =
                new AccountStateRepositoryAdapter(
                        new MongoConnection(
                                MongoClients.create(mongoDBContainer.getConnectionString())),
                        gson);
    }

    @Test
    public void testRetrive() {
        final String accountId = "0";
        AccountState s = new AccountState(accountId);

        try {
            underTest.save(s);
            underTest.save(s);
        } catch (Exception e) {

        }

        AccountState z = null;

        try {
            z = underTest.retrive(accountId);
        } catch (AccountNotFoundException e) {
            fail();
        }

        assertTrue(z.id().equals(accountId));

        try {
            underTest.retrive("-6");
            fail();
        } catch (Exception e) {
        }
    }
}
