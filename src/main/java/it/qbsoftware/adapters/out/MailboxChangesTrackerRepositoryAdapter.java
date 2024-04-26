package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class MailboxChangesTrackerRepositoryAdapter implements MailboxChangesTrackerRepository {
    private final static String COLLECTION = "submission_changes";
    private final static String TYPE_NAME = "SUBMISSION";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public MailboxChangesTrackerRepositoryAdapter(final Gson gson, final MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public MailboxChangesTracker retrive(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(MailboxChangesTracker mailboxChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
