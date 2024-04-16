package it.qbsoftware.adapters.out;

import java.util.Optional;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import it.qbsoftware.adapters.in.jmaplib.SessionResourceAdapter;
import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.SessionResource;

public class UserSessionResourceRepositoryAdapter implements UserSessionResourceRepository {
    private final static String COLLECTION = "Session";
    private final MongoConnection mongoConnection = new MongoConnection();

    @Override
    public Optional<SessionResourcePort> retrieve(String username) {
        FindIterable<Document> docs = mongoConnection.getDatabase().getCollection(COLLECTION)
                .find(Filters.eq("_id", username));

        try {
            if (docs.first() != null) {
                return Optional.of(new SessionResourceAdapter(
                        MongoConnection.gson.fromJson(docs.first().toJson(), SessionResource.class)));
            }
        } catch (final Exception e) {
        }

        return Optional.empty();
    }

    // FIXME: in realtà non serve
    public Boolean save(final String username, final SessionResourcePort sessionResourcePort) {
        final SessionResourceAdapter sessionResourceAdapter = (SessionResourceAdapter) sessionResourcePort;

        Document sessionDoc;
        try {
            sessionDoc = Document.parse(MongoConnection.gson.toJson((sessionResourceAdapter.adaptee())));
        } catch (Exception e) {
            return false;
        }

        sessionDoc.put("_id", username);
        try {
            mongoConnection.getDatabase().getCollection("Session").insertOne(sessionDoc);
        } catch (final Exception e) {
            return false;
        }

        return true;
    }

}
