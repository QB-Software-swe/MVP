package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class ThreadChangesTrackerRepositoryAdapter implements ThreadChangesTrackerRepository {
    private final static String COLLECTION = "submission_changes";
    private final static String TYPE_NAME = "SUBMISSION";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public ThreadChangesTrackerRepositoryAdapter(final Gson gson, final MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public ThreadChangesTracker retrive(final String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(final ThreadChangesTracker threadChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
