package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailSubmissionChangesTracker;
import it.qbsoftware.business.ports.out.domain.EmailSubmissionChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class EmailSubmissionChangesTrackerRepositoryAdapter implements EmailSubmissionChangesTrackerRepository {
    private final static String COLLECTION = "submission_changes";
    private final static String TYPE_NAME = "SUBMISSION";
    private final MongoConnection mongoConnection;
    private final Gson gson;

    @Inject
    public EmailSubmissionChangesTrackerRepositoryAdapter(final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public EmailSubmissionChangesTracker retrive(final String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

}
