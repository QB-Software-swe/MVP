package it.qbsoftware.adapters.out;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;
import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.persistance.MongoConnection;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import rs.ltt.jmap.common.entity.Thread;
import rs.ltt.jmap.gson.JmapAdapters;

public class ThreadRepositoryAdapterTest {
    private ThreadRepositoryAdapter underTest;

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
                new ThreadRepositoryAdapter(
                        new MongoConnection(
                                MongoClients.create(mongoDBContainer.getConnectionString())),
                        gson);
    }

    @Test
    void testRetrive() {

        underTest.save(new ThreadAdapter(Thread.builder().id("x").build()));

        var r = underTest.retrive(new String[] {"x", "y"});

        assertArrayEquals(
                Arrays.asList(r.found()).stream().map(e -> e.getId()).toArray(String[]::new),
                new String[] {"x"});
        assertArrayEquals(r.notFound(), new String[] {"y"});
    }

    @Test()
    void testRetriveOne() {

        underTest.save(new ThreadAdapter(Thread.builder().id("x").build()));

        ThreadPort r = null;

        r = underTest.retriveOne("x");

        assertTrue(r.getId().equals("x"));
    }

    @Test
    void testRetriveAll() {

        underTest.save(new ThreadAdapter(Thread.builder().id("x/").build()));

        var r = underTest.retriveAll("x");

        assertArrayEquals(
                Arrays.asList(r.found()).stream().map(e -> e.getId()).toArray(String[]::new),
                new String[] {"x/"});
    }

    @Test
    void testSave() {}
}
