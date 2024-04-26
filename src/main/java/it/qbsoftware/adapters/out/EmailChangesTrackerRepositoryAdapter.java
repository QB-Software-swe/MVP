package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class EmailChangesTrackerRepositoryAdapter implements EmailChangesTrackerRepository {
    private final static String COLLECTION = "email_changes";
    private final static String TYPE_NAME = "EMAIL";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public EmailChangesTrackerRepositoryAdapter(final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public EmailChangesTracker retrive(final String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(final EmailChangesTracker emailChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
