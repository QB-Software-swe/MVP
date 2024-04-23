package it.qbsoftware.business.domain.methodcall.process.set.create;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;

public class StandardCreateIdentity implements CreateIdentity {
    private final AccountStateRepository accountStateRepository;
    private final IdentityChangesTrackerRepository identityChangesTrackerRepository;
    private final IdentityRepository identityRepository;
    private final SetErrorEnumPort setErrorEnumPort;

    public StandardCreateIdentity(final AccountStateRepository accountStateRepository,
            final IdentityChangesTrackerRepository identityChangesTrackerRepository,
            final IdentityRepository identityRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        this.accountStateRepository = accountStateRepository;
        this.identityChangesTrackerRepository = identityChangesTrackerRepository;
        this.identityRepository = identityRepository;
        this.setErrorEnumPort = setErrorEnumPort;
    }

    @Override
    public CreatedResult<IdentityPort> create(final SetIdentityMethodCallPort setIdentityMethodCallPort)
            throws AccountNotFoundException {
        final Map<String, IdentityPort> created = new HashMap<>();
        final Map<String, SetErrorPort> notCreated = new HashMap<>();

        for (final Map.Entry<String, IdentityPort> entryIdentityToCreate : setIdentityMethodCallPort.getCreate()
                .entrySet()) {
            try {
                IdentityPort createdIdentityPort = createIdentity(entryIdentityToCreate.getValue(),
                        setIdentityMethodCallPort.accountId());
                created.put(entryIdentityToCreate.getKey(), createdIdentityPort);
            } catch (final SetSingletonException setSingletonException) {
                notCreated.put(entryIdentityToCreate.getKey(), setErrorEnumPort.singleton());
            }
        }

        return new CreatedResult<>(created, notCreated);
    }

    private IdentityPort createIdentity(final IdentityPort identityToCreate, final String accountId)
            throws AccountNotFoundException, SetSingletonException {
        final String identityId = accountId + "/" + UUID.randomUUID().toString(); // NOTA: no singletono ma duplicati

        final IdentityPort identityToSave = identityToCreate.getBuilder().id(identityId).build();
        final IdentityPort identityDiff = identityToCreate.getBuilder().reset().id(identityId).build();

        identityRepository.save(identityToSave);
        updateIdentityChanges(identityId, accountId);

        return identityDiff;
    }

    private void updateIdentityChanges(final String identityId, final String accountId)
            throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final IdentityChangesTracker identityChangesTracker = identityChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseIdentityState();
        identityChangesTracker.identityHasBeenCreated(accountState.identityState(), identityId);

        accountStateRepository.save(accountState);
        identityChangesTrackerRepository.save(identityChangesTracker);
    }
}
