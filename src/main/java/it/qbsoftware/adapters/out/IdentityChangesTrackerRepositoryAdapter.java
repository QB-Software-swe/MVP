package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class IdentityChangesTrackerRepositoryAdapter implements IdentityChangesTrackerRepository {
    private final static String COLLECTION = "submission_changes";
    private final static String TYPE_NAME = "SUBMISSION";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public IdentityChangesTrackerRepositoryAdapter(final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public IdentityChangesTracker retrive(final String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(final IdentityChangesTracker identityChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
