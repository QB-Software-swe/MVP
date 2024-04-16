package it.qbsoftware.persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import rs.ltt.jmap.gson.JmapAdapters;

public class MongoConnection {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;

    // FIXME: Spostare
    public static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        gson = gsonBuilder.create();
    }

    public MongoConnection() {
        try {
            mongoClient = MongoClients.create("mongodb://rootuser:rootpass@dbhost:27017/");
            mongoDatabase = mongoClient.getDatabase("jmap");
        } catch (Exception exception) {
            System.exit(1);
        }
    }

    public MongoDatabase getDatabase() {
        return mongoDatabase;
    }
}
