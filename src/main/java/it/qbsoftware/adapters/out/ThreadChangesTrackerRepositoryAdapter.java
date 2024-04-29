package it.qbsoftware.adapters.out;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;

import it.qbsoftware.business.domain.entity.changes.tracker.SimpleThreadChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class ThreadChangesTrackerRepositoryAdapter implements ThreadChangesTrackerRepository {
    private final static String COLLECTION = "thread_changes";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public ThreadChangesTrackerRepositoryAdapter(final Gson gson, final MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public ThreadChangesTracker retrive(final String accountId) {
        final Bson filter = Filters.eq("_id", accountId + "/" + "THREAD");
        final FindIterable<Document> findIterable = mongoConnection.getDatabase().getCollection(COLLECTION)
                .find(filter);

        final var threadChangesTrackerDoc = findIterable.first();

        if (threadChangesTrackerDoc != null) {
            return gson.fromJson(threadChangesTrackerDoc.toJson(), SimpleThreadChangesTracker.class);
        } else {
            return new SimpleThreadChangesTracker(accountId + "/" + "THREAD");
        }
    }

    @Override
    public void save(final ThreadChangesTracker threadChangesTracker) {
        // FIXME: interfaccia?
        final Document threadChangesTrackerDoc = Document.parse(gson.toJson(threadChangesTracker));
        threadChangesTrackerDoc.put("_id", threadChangesTracker.id());
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().upsert(true);
        var i = mongoConnection.getDatabase().getCollection(COLLECTION)
                .findOneAndReplace(Filters.eq("_id", threadChangesTracker.id()), threadChangesTrackerDoc, options);
    }

}
