package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.SimpleMailboxChangesTracker;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MailboxChangesTrackerRepositoryAdapter implements MailboxChangesTrackerRepository {
    private static final String COLLECTION = "mailbox_changes";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public MailboxChangesTrackerRepositoryAdapter(
            final Gson gson, final MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public MailboxChangesTracker retrive(final String accountId) {
        final Bson filter = Filters.eq("_id", accountId + "/" + "MAILBOX");
        final FindIterable<Document> findIterable =
                mongoConnection.getDatabase().getCollection(COLLECTION).find(filter);

        final var threadChangesTrackerDoc = findIterable.first();

        if (threadChangesTrackerDoc != null) {
            return gson.fromJson(
                    threadChangesTrackerDoc.toJson(), SimpleMailboxChangesTracker.class);
        } else {
            return new SimpleMailboxChangesTracker(accountId + "/" + "MAILBOX");
        }
    }

    @Override
    public void save(final MailboxChangesTracker mailboxChangesTracker) {
        final Document mailboxChangesTrackerDoc =
                Document.parse(gson.toJson(mailboxChangesTracker));
        mailboxChangesTrackerDoc.put("_id", mailboxChangesTracker.id());
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().upsert(true);
        mongoConnection
                .getDatabase()
                .getCollection(COLLECTION)
                .findOneAndReplace(
                        Filters.eq("_id", mailboxChangesTracker.id()),
                        mailboxChangesTrackerDoc,
                        options);
    }
}
