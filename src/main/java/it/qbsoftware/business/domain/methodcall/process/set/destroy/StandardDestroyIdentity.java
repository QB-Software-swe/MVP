package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;

public class StandardDestroyIdentity implements DestroyIdentity {
    private final AccountStateRepository accountStateRepository;
    private final IdentityChangesTrackerRepository identityChangesTrackerRepository;
    private final SetErrorEnumPort setErrorEnumPort;
    private final IdentityRepository identityRepository;

    public StandardDestroyIdentity(final AccountStateRepository accountStateRepository,
            final IdentityChangesTrackerRepository identityChangesTrackerRepository,
            final IdentityRepository identityRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        this.accountStateRepository = accountStateRepository;
        this.identityChangesTrackerRepository = identityChangesTrackerRepository;
        this.setErrorEnumPort = setErrorEnumPort;
        this.identityRepository = identityRepository;
    }

    @Override
    public DestroyedResult destroy(final SetIdentityMethodCallPort setIdentityMethodCallPort)
            throws AccountNotFoundException {
        final List<String> destroyed = new ArrayList<>();
        final Map<String, SetErrorPort> notDestroyed = new HashMap<>();

        for (final String identityIdToDestroy : setIdentityMethodCallPort.getDestroy()) {
            try {
                destroyIdentity(identityIdToDestroy, identityIdToDestroy);
                destroyed.add(identityIdToDestroy);
            } catch (final SetNotFoundException setNotFoundException) {
                notDestroyed.put(identityIdToDestroy, setErrorEnumPort.notFound());
            }
        }

        return new DestroyedResult(destroyed.toArray(String[]::new), notDestroyed);
    }

    private void destroyIdentity(final String idToDestroy, final String accountId)
            throws AccountNotFoundException, SetNotFoundException {
        identityRepository.destroy(idToDestroy);
        updateIdentityChanges(idToDestroy, accountId);
    }

    private void updateIdentityChanges(final String identityId, final String accountId)
            throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final IdentityChangesTracker identityChangesTracker = identityChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseState();
        identityChangesTracker.identityHasBeenDestroyed(accountState.state(), identityId);

        accountStateRepository.save(accountState);
        identityChangesTrackerRepository.save(identityChangesTracker);
    }
}
