package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.SimpleIdentityChangesTracker;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class IdentityChangesTrackerRepositoryAdapter implements IdentityChangesTrackerRepository {
    private static final String COLLECTION = "identity_changes";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public IdentityChangesTrackerRepositoryAdapter(
            final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public IdentityChangesTracker retrive(final String accountId) {
        final Bson filter = Filters.eq("_id", accountId + "/" + "IDENTITY");
        final FindIterable<Document> findIterable =
                mongoConnection.getDatabase().getCollection(COLLECTION).find(filter);

        final var identityChangesTrackerDoc = findIterable.first();

        if (identityChangesTrackerDoc != null) {
            return gson.fromJson(
                    identityChangesTrackerDoc.toJson(), SimpleIdentityChangesTracker.class);
        } else {
            return new SimpleIdentityChangesTracker(accountId + "/" + "IDENTITY");
        }
    }

    @Override
    public void save(final IdentityChangesTracker identityChangesTracker) {
        final Document identityChangesTrackerDoc =
                Document.parse(gson.toJson(identityChangesTracker));
        identityChangesTrackerDoc.put("_id", identityChangesTracker.id());
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().upsert(true);
        mongoConnection
                .getDatabase()
                .getCollection(COLLECTION)
                .findOneAndReplace(
                        Filters.eq("_id", identityChangesTracker.id()),
                        identityChangesTrackerDoc,
                        options);
    }
}
