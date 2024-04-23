package it.qbsoftware.persistance;

import com.google.inject.Inject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private final MongoDatabase jmapDatabase;

    @Inject
    public MongoConnection(final MongoClient mongoClient) {
        this.jmapDatabase = mongoClient.getDatabase("jmap"); // FIXME: envs
    }

    public MongoDatabase getDatabase() {
        return jmapDatabase;
    }
}
