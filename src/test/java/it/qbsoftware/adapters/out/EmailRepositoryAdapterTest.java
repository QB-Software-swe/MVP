package it.qbsoftware.adapters.out;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.persistance.MongoConnection;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.gson.JmapAdapters;

public class EmailRepositoryAdapterTest {
    private EmailRepositoryAdapter underTest;

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
                new EmailRepositoryAdapter(
                        new MongoConnection(
                                MongoClients.create(mongoDBContainer.getConnectionString())),
                        gson);
    }

    @Test
    void testDestroy() {
        try {
            underTest.save(new EmailAdapter(Email.builder().id("x").build()));
        } catch (SetSingletonException e) {
            fail();
        }

        EmailPort x = null;
        try {
            x = underTest.destroy("x");
        } catch (SetNotFoundException e) {
            fail();
        }

        assertTrue(x.getId().equals("x"));
        assertThrows(SetNotFoundException.class, () -> underTest.destroy(null));
    }

    @Test
    void testOverwrite() {}

    @Test
    void testRetrive() {
        try {
            underTest.save(new EmailAdapter(Email.builder().id("x").build()));
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
            underTest.save(new EmailAdapter(Email.builder().id("x/").build()));
        } catch (SetSingletonException e) {
            fail();
        }

        var r = underTest.retriveAll("x");

        assertArrayEquals(
                Arrays.asList(r.found()).stream().map(e -> e.getId()).toArray(String[]::new),
                new String[] {"x/"});
    }

    @Test()
    void testRetriveOne() {
        try {
            underTest.save(new EmailAdapter(Email.builder().id("x").build()));
        } catch (SetSingletonException e) {
            fail();
        }

        EmailPort r = null;
        try {
            r = underTest.retriveOne("x");
        } catch (SetNotFoundException e) {
            fail();
        }

        assertTrue(r.getId().equals("x"));

        assertThrows(SetNotFoundException.class, () -> underTest.retriveOne(null));
    }

    @Test
    void testSave() {}
}
