package it.qbsoftware.business.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import it.qbsoftware.business.ports.in.jmap.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

// Leak, implementare
import rs.ltt.jmap.common.entity.Email;

public class GetEmailMethodCallService implements GetEmailMethodCallUsecase {
    final ResultReferenceResolverPort resultReferenceResolverPort;
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    final EmailRepository emailRepository;
    final EmailBuilderPort emailBuilderPort;
    final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort;

    public GetEmailMethodCallService(final EmailRepository emailRepository,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
            final ResultReferenceResolverPort resultReferencePort,
            GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort, EmailBuilderPort emailBuilderPort) {
        this.resultReferenceResolverPort = resultReferencePort;
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.emailRepository = emailRepository;
        this.emailBuilderPort = emailBuilderPort;
        this.getEmailMethodResponseBuilderPort = getEmailMethodResponseBuilderPort;
    }

    @Override
    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        getEmailMethodResponseBuilderPort.reset();

        // final String accountId = getEmailMethodCallPort.accountId(); FIXME:
        // IMPLEMENTARE LO STATO dell'account
        final InvocationResultReferencePort clientIdsReference = getEmailMethodCallPort.getIdsReference();
        final List<String> ids;

        if (clientIdsReference != null) {
            try {
                ids = Arrays.asList(resultReferenceResolverPort.resolve(clientIdsReference, previousResponses));
            } catch (final IllegalArgumentException exception) {
                return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
            }
        } else {
            ids = Arrays.asList(getEmailMethodCallPort.getIds());
        }

        final String[] properties = getEmailMethodCallPort.getProperties();

        Stream<EmailPort> emailStream = Arrays.asList(emailRepository.retrive(ids.toArray(new String[0]))).stream();

        if (Arrays.equals(properties, Email.Properties.THREAD_ID)) { // FIXME:
            EmailBuilderPort emailBuilder = emailBuilderPort;
            emailBuilder.reset();
            emailStream = emailStream.map(
                    email -> emailBuilder
                            .id(email.getId())
                            .threadId(email.getThreadId())
                            .build());
        } else if (Arrays.equals(properties, Email.Properties.MUTABLE)) {
            EmailBuilderPort emailBuilder = emailBuilderPort;
            emailBuilder.reset();
            emailStream = emailStream.map(
                    email -> emailBuilder
                            .id(email.getId())
                            .keywords(email.getKeywords())
                            .mailboxIds(email.getMailboxIds())
                            .build());
        }

        return new MethodResponsePort[] {
                getEmailMethodResponseBuilderPort
                        .list(emailStream.toArray(EmailPort[]::new))
                        .state("0") // TODO: implementare veramente il retrive dello state dell'e-mail
                        .build()
        };
    }

}
