package it.qbsoftware.persistance;

import com.google.inject.Inject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private final MongoClient mongoClient;
    private final MongoDatabase jmapDatabase;

    @Inject
    public MongoConnection(final MongoClient mongoClient) {
        this.mongoClient = mongoClient; //FIXME: remove?
        this.jmapDatabase = mongoClient.getDatabase("jmap"); // FIXME: envs
    }

    public MongoDatabase getDatabase() {
        return jmapDatabase;
    }

    public MongoClient getMongoClient() {
        return this.mongoClient;
    }
}
