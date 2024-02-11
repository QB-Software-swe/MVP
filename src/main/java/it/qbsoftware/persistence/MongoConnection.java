package it.qbsoftware.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
  MongoClient mongoClient;
  MongoDatabase mongoDatabase;

  public MongoConnection() {
    mongoClient = MongoClients.create("mongodb://rootuser:rootpass@localhost:27017/");
    mongoDatabase = mongoClient.getDatabase("jmap");
  }

  public MongoDatabase getDatabase() {
    return mongoDatabase;
  }
}
