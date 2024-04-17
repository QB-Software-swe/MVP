package it.qbsoftware.business.services.get;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class GetThreadMethodCallService implements GetThreadMethodCallUsecase {
    final ResultReferenceResolverPort referenceResolverPort;
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    final EmailRepository emailRepository;
    final ThreadBuilderPort threadBuilderPort;
    final it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort;

    public GetThreadMethodCallService(final ResultReferenceResolverPort referenceResolverPort,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
            final EmailRepository emailRepository, final ThreadBuilderPort threadBuilderPort,
            final GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort) {
        this.referenceResolverPort = referenceResolverPort;
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.emailRepository = emailRepository;
        this.threadBuilderPort = threadBuilderPort;
        this.getThreadMethodResponseBuilderPort = getThreadMethodResponseBuilderPort;
    }

    @Override
    public MethodResponsePort[] call(final GetThreadMethodCallPort getThreadMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previouseResponses) {

        final InvocationResultReferencePort clientIdsReference = getThreadMethodCallPort.getIdsReference();
        final List<String> ids;

        if (clientIdsReference != null) {
            try {
                ids = Arrays.asList(referenceResolverPort.resolve(clientIdsReference, previouseResponses));
            } catch (final IllegalArgumentException exception) {
                return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
            }
        } else {
            ids = Arrays.asList(getThreadMethodCallPort.getIds());
        }

        Map<String, EmailPort> emails = Arrays.asList(emailRepository.retriveAll(getThreadMethodCallPort.accountId()))
                .stream().collect(Collectors.toMap(EmailPort::getId, Function.identity()));

        ThreadBuilderPort threadBuilder = threadBuilderPort;
        final ThreadPort[] threads = ids.stream().map(
                threadId -> threadBuilder.reset()
                        .id(threadId)
                        .emailIds(
                                emails.values().stream()
                                        .filter(
                                                email -> email.getThreadId()
                                                        .equals(threadId))
                                        .sorted(Comparator.comparing(EmailPort::getReceivedAt))
                                        .map(EmailPort::getId)
                                        .collect(Collectors.toList()))
                        .build())
                .toArray(ThreadPort[]::new);

        return new MethodResponsePort[] {
                getThreadMethodResponseBuilderPort.list(threads).state("0").build() // FIXME: fare lo stato dei threads
        };
    }

}
