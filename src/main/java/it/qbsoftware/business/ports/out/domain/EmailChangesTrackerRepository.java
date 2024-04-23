package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;

public interface EmailChangesTrackerRepository {
    public EmailChangesTracker retrive(String accountId);

    public void save(final EmailChangesTracker emailChangesTracker);
}
