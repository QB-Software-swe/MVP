package it.qbsoftware.adapters.out;

import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;

public class IdentityChangesTrackerRepositoryAdapter implements IdentityChangesTrackerRepository {

    @Override
    public IdentityChangesTracker retrive(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrive'");
    }

    @Override
    public void save(IdentityChangesTracker identityChangesTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
