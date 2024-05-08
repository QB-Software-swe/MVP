package it.qbsoftware.adapters.out;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;
import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.persistance.MongoConnection;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.gson.JmapAdapters;

public class IdentityRepositoryAdapterTest {
    private IdentityRepositoryAdapter underTest;

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
                new IdentityRepositoryAdapter(
                        new MongoConnection(
                                MongoClients.create(mongoDBContainer.getConnectionString())),
                        gson);
    }

    @Test
    void testDestroy() {

        String objectId = "x";
        try {
            underTest.save(new IdentityAdapter(Identity.builder().id(objectId).build()));
        } catch (SetSingletonException e) {
            fail();
        }

        try {
            underTest.destroy(objectId);
        } catch (SetNotFoundException e) {
            fail();
        }

        assertTrue(underTest.retriveAll(objectId).notFound().length == 0);
    }

    @Test
    void testRetrive() {
        try {
            underTest.save(new IdentityAdapter(Identity.builder().id("x").build()));
        } catch (SetSingletonException e) {
            fail();
        }

        var r = underTest.retrive(new String[] {"x", "y"});

        assertArrayEquals(
                Arrays.asList(r.found()).stream().map(e -> e.getId()).toArray(String[]::new),
                new String[] {"x"});
        assertArrayEquals(r.notFound(), new String[] {"y"});
    }

    @Test
    void testRetriveAll() {
        try {
            underTest.save(new IdentityAdapter(Identity.builder().id("x/").build()));
        } catch (SetSingletonException e) {
            fail();
        }

        var r = underTest.retriveAll("x");

        assertArrayEquals(
                Arrays.asList(r.found()).stream().map(e -> e.getId()).toArray(String[]::new),
                new String[] {"x/"});
    }

    @Test
    void testSave() {}
}
