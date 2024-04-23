package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailSubmissionChangesTracker;

public interface EmailSubmissionChangesTrackerRepository {
    public EmailSubmissionChangesTracker retrive(String accountId);

}
