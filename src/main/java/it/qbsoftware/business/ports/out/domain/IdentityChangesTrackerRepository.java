package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;

public interface IdentityChangesTrackerRepository {

    public IdentityChangesTracker retrive(String accountId);

    public void save(IdentityChangesTracker identityChangesTracker);
}
