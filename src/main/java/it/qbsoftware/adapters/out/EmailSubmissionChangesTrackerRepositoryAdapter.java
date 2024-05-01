package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailSubmissionChangesTracker;
import it.qbsoftware.business.ports.out.domain.EmailSubmissionChangesTrackerRepository;
import it.qbsoftware.persistance.MongoConnection;

public class EmailSubmissionChangesTrackerRepositoryAdapter implements EmailSubmissionChangesTrackerRepository {
    @SuppressWarnings("unused")
    private final static String COLLECTION = "submission_changes";
    @SuppressWarnings("unused")
    private final static String TYPE_NAME = "SUBMISSION";
    @SuppressWarnings("unused")
    private final MongoConnection mongoConnection;
    @SuppressWarnings("unused")
    private final Gson gson;

    @Inject
    public EmailSubmissionChangesTrackerRepositoryAdapter(final MongoConnection mongoConnection, final Gson gson) {
        this.mongoConnection = mongoConnection;
        this.gson = gson;
    }

    @Override
    public EmailSubmissionChangesTracker retrive(final String accountId) {
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

}
