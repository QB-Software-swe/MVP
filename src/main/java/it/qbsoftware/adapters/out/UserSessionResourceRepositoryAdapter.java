package it.qbsoftware.adapters.out;

import java.util.Optional;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.SessionResource;

public class UserSessionResourceRepositoryAdapter implements UserSessionResourceRepository {
    private final static String COLLECTION = "session";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public UserSessionResourceRepositoryAdapter(final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public Optional<SessionResourcePort> retrieve(final String username) {
        FindIterable<Document> docs = mongoConnection.getDatabase().getCollection(COLLECTION)
                .find(Filters.eq("_id", username));

        try {
            if (docs.first() != null) {
                return Optional.of(new SessionResourceAdapter(
                        gson.fromJson(docs.first().toJson(), SessionResource.class)));
            }
        } catch (final Exception e) {
            e.printStackTrace(); //REMOVEME
        }

        return Optional.empty();
    }

    public Boolean save(final String username, final SessionResourcePort sessionResourcePort) {
        final SessionResourceAdapter sessionResourceAdapter = (SessionResourceAdapter) sessionResourcePort;

        Document sessionDoc;
        try {
            sessionDoc = Document.parse(gson.toJson((sessionResourceAdapter.adaptee())));
        } catch (Exception e) {
            return false;
        }

        sessionDoc.put("_id", username);
        try {
            mongoConnection.getDatabase().getCollection(COLLECTION).insertOne(sessionDoc);
        } catch (final Exception e) {
            return false;
        }

        return true;
    }

}
