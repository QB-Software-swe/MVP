package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;

public interface ThreadChangesTrackerRepository {
    public ThreadChangesTracker retrive(final String accountId);

    public void save(final ThreadChangesTracker threadChangesTracker);
}
