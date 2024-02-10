package it.qbsoftware.persistence;

public enum MongoConnectionSingleton {
    INSTANCE;
    
    MongoConnection mongoConnection = new MongoConnection();

    public MongoConnection getConnection() {
        return mongoConnection;
    }
}
