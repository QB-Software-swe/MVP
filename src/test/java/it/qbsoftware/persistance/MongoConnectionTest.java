package it.qbsoftware.persistance;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;

public class MongoConnectionTest {
    @Test
    void testGetDatabase()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        MongoClient mongoClient = mock(MongoClient.class);
        MongoDatabase mongoDatabase = mock(MongoDatabase.class);

        when(mongoClient.getDatabase("jmap")).thenReturn(mongoDatabase);
        MongoConnection mongoConnection = new MongoConnection(mongoClient);
        MongoDatabase result = mongoConnection.getDatabase();

        assertEquals(mongoDatabase, result);
    }
}
