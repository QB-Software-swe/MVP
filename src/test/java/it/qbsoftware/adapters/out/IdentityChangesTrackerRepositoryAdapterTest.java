package it.qbsoftware.adapters.out;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import it.qbsoftware.business.domain.entity.changes.tracker.SimpleIdentityChangesTracker;
import it.qbsoftware.persistance.MongoConnection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import rs.ltt.jmap.gson.JmapAdapters;

public class IdentityChangesTrackerRepositoryAdapterTest {
    private IdentityChangesTrackerRepositoryAdapter underTest;

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
                new IdentityChangesTrackerRepositoryAdapter(
                        new MongoConnection(
                                MongoClients.create(mongoDBContainer.getConnectionString())),
                        gson);
    }

    @Test
    void testRetrive() {
        final String id = "exampleDoc/IDENTITY";
        final Document doc = Document.parse(gson.toJson(new SimpleIdentityChangesTracker(id)));
        MongoClients.create(mongoDBContainer.getConnectionString())
                .getDatabase("jmap")
                .getCollection("identity_changes")
                .insertOne(doc);

        var retrived = underTest.retrive("exampleDoc");
        var retrivedButNotExist = underTest.retrive("exampleDoc3232");

        assertTrue(retrived.id().equals(id));
        assertTrue(!retrivedButNotExist.id().equals(id));
    }

    @Test
    void testSave() {
        final String id = "exampleDoc/IDENTITY";
        underTest.save(new SimpleIdentityChangesTracker(id));

        var result =
                MongoClients.create(mongoDBContainer.getConnectionString())
                        .getDatabase("jmap")
                        .getCollection("identity_changes")
                        .find(Filters.eq("_id", id));

        assertTrue(
                gson.fromJson(result.first().toJson(), SimpleIdentityChangesTracker.class)
                        .id()
                        .equals(id));
    }
}
