package it.qbsoftware.business.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import java.time.Instant;

import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.CreationIdResolver;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.usecase.SetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class SetEmailMethodCallService implements SetEmailMethodCallUsecase {
    final AccountStateRepository accountStateRepository;
    final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort;
    final EmailRepository emailRepository;
    final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;
    final EmailBuilderPort emailBuilderPort;
    final EmailBodyPartBuilderPort emailBodyPartBuilderPort;

    public SetEmailMethodCallService(final AccountStateRepository accountStateRepository,
            final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses, EmailBuilderPort emailBuilderPort,
            EmailRepository emailRepository, SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort,
            EmailBodyPartBuilderPort emailBodyPartBuilderPort) {
        this.accountStateRepository = accountStateRepository;
        this.stateMismatchMethodErrorResponsePort = stateMismatchMethodErrorResponsePort;
        this.emailRepository = emailRepository;
        this.setEmailMethodResponseBuilderPort = setEmailMethodResponseBuilderPort;
        this.emailBuilderPort = emailBuilderPort;
        this.emailBodyPartBuilderPort = emailBodyPartBuilderPort;
    }

    @Override
    public MethodResponsePort[] call(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        final String accountId = setEmailMethodCallPort.accountId();
        AccountState accountState = accountStateRepository.retrive(accountId);

        final Map<String, EmailPort> emailsToCreate = setEmailMethodCallPort.getCreate();
        final Map<String, Map<String, Object>> emailsToUpdate = setEmailMethodCallPort.getUpdate();
        final String[] emailsToDestroy = setEmailMethodCallPort.getDestroy();

        if (ifInStateMismatch(accountState, setEmailMethodCallPort.getIfInState())) {
            return new MethodResponsePort[] { stateMismatchMethodErrorResponsePort };
        }

        if (emailsToDestroy != null) {
            DestroyEmailResponse destroyEmailResponse = processDestroyEmail(accountId, emailsToDestroy);
            // FIXME: implement destroy email response
        }

        if (emailsToCreate != null && emailsToCreate.size() > 0) {
            processCreateEmail(accountId, emailsToCreate, accountState, previousResponses);
        }

        if (emailsToUpdate != null && emailsToUpdate.size() > 0) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'call'");
        }

        return new MethodResponsePort[] { setEmailMethodResponseBuilderPort.build() };
    }

    boolean ifInStateMismatch(final AccountState accountState, final String methodCallState) {
        return !accountState.emailState().equals(methodCallState) && methodCallState != null;
    }

    DestroyEmailResponse processDestroyEmail(final String accountId, final String[] emailsToDestroy) {
        ArrayList<String> destroyEmail = new ArrayList<>();
        ArrayList<String> notDestroyEmail = new ArrayList<>();

        for (final String emailId : emailsToDestroy) {
            if (emailRepository.destroy(emailId)) {
                destroyEmail.add(emailId);
                // TODO: modificare lo stato delle e-mail
            } else {
                notDestroyEmail.add(emailId); // Nota: assumiamo che se l'e-mail non venga trovata
                // TODO: modificare lo stato delle e-mail
            }
        }

        return new DestroyEmailResponse(destroyEmail.toArray(String[]::new), notDestroyEmail.toArray(String[]::new));
    }

    void processCreateEmail(final String accountId, final Map<String, EmailPort> emailsToCreate,
            AccountState accountState, final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        for (final Map.Entry<String, EmailPort> emailToCreate : emailsToCreate.entrySet()) {
            final String clientReferenceEmailId = emailToCreate.getKey();
            final EmailPort userSuppliedEmail = emailToCreate.getValue();
            final Map<String, Boolean> mailboxMap = userSuppliedEmail.getMailboxIds();

            final String serverEmailId = accountId + "." + UUID.randomUUID().toString(); // FIXME: id e-mail random ->
                                                                                         // conflitti
            final String serverEmailThreadId = accountId + "." + UUID.randomUUID().toString(); // FIXME: come sopra

            EmailBuilderPort emailBuilder = userSuppliedEmail.toBuilder()
                    .id(serverEmailId)
                    .threadId(serverEmailThreadId)
                    .receivedAt(Instant.now());

            emailBuilder.clearMailboxIds();
            for (Map.Entry<String, Boolean> mailboxEntry : mailboxMap.entrySet()) {
                final String mailboxId = CreationIdResolver.resolveIfNecessary(mailboxEntry.getKey(),
                        previousResponses);
                emailBuilder.mailboxId(mailboxId, mailboxEntry.getValue());
            }

            final List<EmailBodyPartPort> attachments = userSuppliedEmail.getAttachments();
            emailBuilder.clearAttachments();

            if (attachments != null) {
                for (final EmailBodyPartPort attachment : attachments) {
                    final String partId = attachment.getPartId();
                    final EmailBodyValuePort value = partId == null ? null
                            : userSuppliedEmail.getBodyValues().get(partId);

                    if (value != null) {
                        final EmailBodyPartPort emailBodyPartPort = injectId(attachment);
                        emailBuilder.attachment(emailBodyPartPort);
                    } else {
                        emailBuilder.attachment(attachment);
                    }
                }
            }

            final EmailPort emailPort = emailBuilder.build();

            emailRepository.save(emailPort);
            accountState.increaseEmailState();

            setEmailMethodResponseBuilderPort.created(clientReferenceEmailId, emailPort);
        }
    }

    private EmailBodyPartPort injectId(final EmailBodyPartPort attachment) {
        EmailBodyPartBuilderPort emailBodyPartBuilder = emailBodyPartBuilderPort;
        return emailBodyPartBuilder
                .blobId(UUID.randomUUID().toString()) // TODO: controllare la correttezza
                .charset(attachment.getCharset())
                .type(attachment.getType())
                .name(attachment.getName())
                .size(attachment.getSize())
                .build();
    }
}

record DestroyEmailResponse(String[] destroyEmail, String[] notDestroyEmail) {
}