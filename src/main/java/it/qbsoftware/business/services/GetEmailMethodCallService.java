package it.qbsoftware.business.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import it.qbsoftware.business.ports.in.jmap.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.usecase.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

// Leak, implementare
import rs.ltt.jmap.common.entity.Email;

public class GetEmailMethodCallService implements GetEmailMethodCallUsecase {
    final ResultReferencePort resultReferencePort;
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    final EmailRepository emailRepository;
    final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort;

    public GetEmailMethodCallService(final EmailRepository emailRepository,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
            final ResultReferencePort resultReferencePort,
            GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort) {
        this.resultReferencePort = resultReferencePort;
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.emailRepository = emailRepository;
        this.getEmailMethodResponseBuilderPort = getEmailMethodResponseBuilderPort;
    }

    @Override
    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        getEmailMethodResponseBuilderPort.reset();

        final String accountId = getEmailMethodCallPort.accountId();
        final InvocationResultReferencePort clientIdsReference = getEmailMethodCallPort.getIdsReference();
        final List<String> ids;

        if (clientIdsReference != null) {
            try {
                ids = Arrays.asList(resultReferencePort.resolve(clientIdsReference, previousResponses));
            } catch (final IllegalArgumentException exception) {
                return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
            }
        } else {
            ids = Arrays.asList(getEmailMethodCallPort.getIds());
        }

        final String[] properties = getEmailMethodCallPort.getProperties();

        Stream<EmailPort> emailStream = Arrays.asList(emailRepository.retrive(accountId)).stream();

        if (Arrays.equals(properties, Email.Properties.THREAD_ID)) { // FIXME:

        } else if (Arrays.equals(properties, Email.Properties.MUTABLE)) {

        }

        return new MethodResponsePort[] { getEmailMethodResponseBuilderPort };
    }

}
