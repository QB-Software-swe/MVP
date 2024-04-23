package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;

public interface MailboxChangesTrackerRepository {

    public MailboxChangesTracker retrive(String accountId);

    public void save(MailboxChangesTracker mailboxChangesTracker);
}
