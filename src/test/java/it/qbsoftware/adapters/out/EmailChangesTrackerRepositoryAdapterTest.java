package it.qbsoftware.adapters.out;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import it.qbsoftware.business.domain.entity.changes.tracker.SimpleEmailChangesTracker;
import it.qbsoftware.persistance.MongoConnection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import rs.ltt.jmap.gson.JmapAdapters;

public class EmailChangesTrackerRepositoryAdapterTest {
    private EmailChangesTrackerRepositoryAdapter underTest;

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
                new EmailChangesTrackerRepositoryAdapter(
                        new MongoConnection(
                                MongoClients.create(mongoDBContainer.getConnectionString())),
                        gson);
    }

    @Test
    void testRetrive() {
        final String id = "exampleDoc/EMAIL";
        final Document doc = Document.parse(gson.toJson(new SimpleEmailChangesTracker(id)));
        MongoClients.create(mongoDBContainer.getConnectionString())
                .getDatabase("jmap")
                .getCollection("email_changes")
                .insertOne(doc);

        var retrived = underTest.retrive("exampleDoc");
        var retrivedButNotExist = underTest.retrive("exampleDoc3232");

        assertTrue(retrived.id().equals(id));
        assertTrue(!retrivedButNotExist.id().equals(id));
    }

    @Test
    void testSave() {
        final String id = "exampleDoc/EMAIL";
        underTest.save(new SimpleEmailChangesTracker(id));

        var result =
                MongoClients.create(mongoDBContainer.getConnectionString())
                        .getDatabase("jmap")
                        .getCollection("email_changes")
                        .find(Filters.eq("_id", id));

        assertTrue(
                gson.fromJson(result.first().toJson(), SimpleEmailChangesTracker.class)
                        .id()
                        .equals(id));
    }
}
