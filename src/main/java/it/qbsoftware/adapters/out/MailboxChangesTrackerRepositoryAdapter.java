package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;

public class MailboxChangesTrackerRepositoryAdapter implements MailboxChangesTrackerRepository {

    @Override
    public MailboxChangesTracker retrive(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(MailboxChangesTracker mailboxChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
