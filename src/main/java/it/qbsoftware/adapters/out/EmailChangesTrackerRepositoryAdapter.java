package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;

public class EmailChangesTrackerRepositoryAdapter implements EmailChangesTrackerRepository {

    @Override
    public EmailChangesTracker retrive(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(EmailChangesTracker emailChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
