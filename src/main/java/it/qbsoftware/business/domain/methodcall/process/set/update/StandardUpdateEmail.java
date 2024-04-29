package it.qbsoftware.business.domain.methodcall.process.set.update;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetInvalidPatchException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class StandardUpdateEmail implements UpdateEmail {
    private final EmailRepository emailRepository;
    private final SetErrorEnumPort setErrorEnumPort;
    private final AccountStateRepository accountStateRepository;
    private final EmailChangesTrackerRepository emailChangesTrackerRepository;
    private final MailboxChangesTrackerRepository mailboxChangesTrackerRepository;
    private final CreationIdResolverPort creationIdResolverPort;

    public StandardUpdateEmail(final EmailRepository emailRepository, final SetErrorEnumPort setErrorEnumPort,
            final AccountStateRepository accountStateRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository, final CreationIdResolverPort creationIdResolverPort) {
        this.emailRepository = emailRepository;
        this.setErrorEnumPort = setErrorEnumPort;
        this.accountStateRepository = accountStateRepository;
        this.emailChangesTrackerRepository = emailChangesTrackerRepository;
        this.mailboxChangesTrackerRepository = mailboxChangesTrackerRepository;
        this.creationIdResolverPort = creationIdResolverPort;
    }

    @Override
    public UpdatedResult<EmailPort> update(final SetEmailMethodCallPort setEmailMethodCall, final ListMultimapPort<String, ResponseInvocationPort> previousResponses) throws AccountNotFoundException {
        final String accountId = setEmailMethodCall.accountId();
        final HashMap<String, EmailPort> updated = new HashMap<>();
        final HashMap<String, SetErrorPort> notUpdated = new HashMap<>();

        final var mapEmailAndPatch = setEmailMethodCall.getUpdate();

        if (mapEmailAndPatch != null) {
            for (final Map.Entry<String, Map<String, Object>> mapEmailIdPatchObject : mapEmailAndPatch.entrySet()) {
                try {
                    final EmailPort emailSideEffectDiff = applayPatches(accountId, mapEmailIdPatchObject.getKey(),
                            mapEmailIdPatchObject.getValue(), previousResponses);

                    updated.put(mapEmailIdPatchObject.getKey(), emailSideEffectDiff);

                } catch (final SetNotFoundException setNotFoundException) {
                    notUpdated.put(mapEmailIdPatchObject.getKey(), setErrorEnumPort.notFound());
                } catch (final SetInvalidPatchException setInvalidPatchException) {
                    notUpdated.put(mapEmailIdPatchObject.getKey(), setErrorEnumPort.invalidPatch());
                }
            }
        }

        return new UpdatedResult<EmailPort>(updated, notUpdated);
    }

    private EmailPort applayPatches(final String accountId, final String emailIdToPatch,
            final Map<String, Object> patchObjects, final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws SetNotFoundException, SetInvalidPatchException, AccountNotFoundException {
        EmailPort emailTarget = emailRepository.retriveOne(emailIdToPatch);
        final EmailBuilderPort emailSideEffectDiffBuilder = emailTarget.toBuilder();
        EmailBuilderPort emailToPatchBuilder = emailTarget.toBuilder();

        for (final Map.Entry<String, Object> patchObject : patchObjects.entrySet()) {
            final String path = patchObject.getKey();
            final Object modification = patchObject.getValue();

            final List<String> pathParts = Arrays.asList(path.split("/"));
            emailToPatchBuilder = switch (pathParts.get(0)) {
                // TODO: aggiungere le parti man mano che le richiede il client
                case "keywords":
                    if (pathParts.size() == 2 && modification instanceof Boolean mBoolean) {
                        updateEmailChanges(emailIdToPatch, accountId);
                        yield emailToPatchBuilder.keywords(pathParts.get(1), mBoolean);
                    }
                    throw new SetInvalidPatchException();

                case "mailboxIds":
                    if (pathParts.size() == 2 && modification instanceof Boolean mBoolean) {
                           final String mailboxId = pathParts.get(1);
                    yield emailToPatchBuilder.mailboxId(mailboxId, mBoolean);
                } else if (modification instanceof Map) {
                    final Map<String, Boolean> mailboxMap = (Map<String, Boolean>) modification;
                    emailToPatchBuilder.clearMailboxIds();
                    for (Map.Entry<String, Boolean> mailboxEntry : mailboxMap.entrySet()) {
                        final String mailboxId =
                                creationIdResolverPort.resolveIfNecessary(
                                        mailboxEntry.getKey(), previousResponses);
                                        yield emailToPatchBuilder.mailboxId(mailboxId, mailboxEntry.getValue());
                    }}
                    throw new SetInvalidPatchException();

                default:
                    throw new SetInvalidPatchException();
            };
        }

        emailRepository.overwrite(emailToPatchBuilder.build());
        return emailSideEffectDiffBuilder.build();
    }

    private void updateEmailChanges(final String emailId, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final EmailChangesTracker emailChangesTracker = emailChangesTrackerRepository.retrive(accountId);

        accountState = accountState.increaseState();
        emailChangesTracker.emailHasBeenCreated(accountState.state(), emailId);

        accountStateRepository.save(accountState);
        emailChangesTrackerRepository.save(emailChangesTracker);
    }

    private void updateMailbox(final String[] mailboxIds, final String accountId) throws AccountNotFoundException {
        AccountState accountState = accountStateRepository.retrive(accountId);
        final MailboxChangesTracker mailboxChangesTracker = mailboxChangesTrackerRepository.retrive(accountId);

        // NOTA: mailboxIds == #di?
        for (final String id : mailboxIds) {
            accountState = accountState.increaseState();
            mailboxChangesTracker.mailboxHasBeenUpdated(accountState.state(), id);
        }

        mailboxChangesTrackerRepository.save(mailboxChangesTracker);
        accountStateRepository.save(accountState);
    }

}
