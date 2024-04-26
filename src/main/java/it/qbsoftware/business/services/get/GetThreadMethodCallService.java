package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;

public class GetThreadMethodCallService implements GetThreadMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final GetReferenceIdsResolver getReferenceIdsResolver;
    private final ThreadRepository threadRepository;
    private final ThreadPropertiesFilter threadPropertiesFilter;
    private final GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort;

    public GetThreadMethodCallService(final AccountStateRepository accountStateRepository,
            final GetReferenceIdsResolver getReferenceIdsResolver,
            final GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort,
            final ThreadPropertiesFilter threadPropertiesFilter,
            final ThreadRepository threadRepository) {
        this.accountStateRepository = accountStateRepository;
        this.getReferenceIdsResolver = getReferenceIdsResolver;
        this.threadRepository = threadRepository;
        this.threadPropertiesFilter = threadPropertiesFilter;
        this.getThreadMethodResponseBuilderPort = getThreadMethodResponseBuilderPort;
    }

    @Override
    public GetThreadMethodResponsePort call(final GetThreadMethodCallPort getThreadMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previouseResponses)
            throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {

        final String accountId = getThreadMethodCallPort.accountId();
        AccountState accountState = accountStateRepository.retrive(accountId);

        final String[] threadIds = getReferenceIdsResolver.resolve(getThreadMethodCallPort, previouseResponses);

        final RetrivedEntity<ThreadPort> threadsRetrived = threadIds != null ? threadRepository.retrive(threadIds)
                : threadRepository.retriveAll(accountId);

        final ThreadPort[] threadsFiltred = threadPropertiesFilter.filter(threadsRetrived.found(),
                getThreadMethodCallPort.getProperties());

        return getThreadMethodResponseBuilderPort
                .reset()
                .list(threadsFiltred)
                .notFound(threadsRetrived.notFound())
                .state(accountState.emailState()).build();
    }
}
