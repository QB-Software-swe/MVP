package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetInvalidPatchException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StandardUpdateMailbox implements UpdateMailbox {
    private final SetErrorEnumPort setErrorEnumPort;
    private final MailboxRepository mailboxRepository;
    private final CreationIdResolverPort creationIdResolverPort;
    private final AccountStateRepository accountStateRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    public StandardUpdateMailbox(
            final SetErrorEnumPort setErrorEnumPort,
            final MailboxRepository mailboxRepository,
            final CreationIdResolverPort creationIdResolverPort,
            final AccountStateRepository accountStateRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository) {
        this.setErrorEnumPort = setErrorEnumPort;
        this.mailboxRepository = mailboxRepository;
        this.creationIdResolverPort = creationIdResolverPort;
        this.accountStateRepository = accountStateRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
    }

    @Override
    public UpdatedResult<MailboxPort> update(
            final SetMailboxMethodCallPort setMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        final HashMap<String, MailboxPort> updated = new HashMap<>();
        final HashMap<String, SetErrorPort> notUpdated = new HashMap<>();

        final var mailboxsToUpdate = setMailboxMethodCallPort.getUpdate();
        if (mailboxsToUpdate != null) {
            for (final Entry<String, Map<String, Object>> x : mailboxsToUpdate.entrySet()) {
                try {
                    var diff =
                            applayPatches(
                                    setMailboxMethodCallPort.accountId(),
                                    x.getKey(),
                                    x.getValue(),
                                    previousResponses);
                    updated.put(x.getKey(), diff);
                } catch (Exception e) {
                    notUpdated.put(x.getKey(), setErrorEnumPort.invalidPatch());
                }
            }
        }

        return new UpdatedResult<>(updated, notUpdated);
    }

    private MailboxPort applayPatches(
            final String accountId,
            final String mailboxId,
            final Map<String, Object> patchObjects,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws SetNotFoundException, SetInvalidPatchException, AccountNotFoundException {
        MailboxPort mailboxTarget =
                mailboxRepository.retriveOne(
                        creationIdResolverPort.resolveIfNecessary(mailboxId, previousResponses));
        MailboxBuilderPort mailboxToPatchBuilder = mailboxTarget.toBuilder();
        final MailboxBuilderPort mailboxSideEffectBuilder = mailboxTarget.toBuilder().reset();

        for (final Map.Entry<String, Object> patchObject : patchObjects.entrySet()) {
            final String path = patchObject.getKey();
            final Object modification = patchObject.getValue();

            final List<String> pathParts = Arrays.asList(path.split("/"));
            mailboxToPatchBuilder =
                    switch (pathParts.get(0)) {
                        case "name":
                            if (pathParts.size() == 2 && modification instanceof String name) {
                                updateMailbox(new String[] {mailboxId}, accountId);
                                yield mailboxToPatchBuilder.name(name);
                            }
                            throw new SetInvalidPatchException();

                        default:
                            throw new SetInvalidPatchException();
                    };
        }

        mailboxRepository.overwrite(mailboxToPatchBuilder.build());
        return mailboxSideEffectBuilder.build();
    }

    private void updateMailbox(final String[] mailboxIds, final String accountId)
            throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker =
                mailboxChangesTrackerRepository.retrive(accountId);

        for (final String id : mailboxIds) {
            accountState = accountState.increaseState();
            mailboxChangesTracker.mailboxHasBeenUpdated(accountState.state(), id);
        }

        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
        accountStateRepository.save(accountState);
    }
}
