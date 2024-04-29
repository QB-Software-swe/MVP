package it.qbsoftware.adapters.out;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.SimpleEmailChangesTracker;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class EmailChangesTrackerRepositoryAdapter implements EmailChangesTrackerRepository {
    private final static String COLLECTION = "email_changes";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public EmailChangesTrackerRepositoryAdapter(final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public EmailChangesTracker retrive(final String accountId) {
        final Bson filter = Filters.eq("_id", accountId + "/" + "EMAIL");
        final FindIterable<Document> findIterable = mongoConnection.getDatabase().getCollection(COLLECTION)
                .find(filter);

        final var emailChangesTrackerDoc = findIterable.first();

        if (emailChangesTrackerDoc != null) {
            return gson.fromJson(emailChangesTrackerDoc.toJson(), SimpleEmailChangesTracker.class);
        } else {
            return new SimpleEmailChangesTracker(accountId + "/" + "EMAIL");
        }
    }

    @Override
    public void save(final EmailChangesTracker emailChangesTracker) {
        // FIXME: interfaccia?
        final Document emailChangesTrackerDoc = Document.parse(gson.toJson(emailChangesTracker));
        emailChangesTrackerDoc.put("_id", emailChangesTracker.id());
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().upsert(true);
        var i = mongoConnection.getDatabase().getCollection(COLLECTION)
                .findOneAndReplace(Filters.eq("_id", emailChangesTracker.id()), emailChangesTrackerDoc, options);
    }

}
