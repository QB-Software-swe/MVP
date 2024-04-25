package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.IdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;

public class GetIdentityMethodCallService implements GetIdentityMethodCallUsecase {
    private final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort;
    private final AccountStateRepository accountStateRepository;
    private final GetReferenceIdsResolver getReferenceIdsResolver;
    private final IdentityRepository identityRepository;
    private final IdentityPropertiesFilter identityPropertiesFilter;

    public GetIdentityMethodCallService(final AccountStateRepository accountStateRepository,
            final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort,
            final GetReferenceIdsResolver getReferenceIdsResolver,
            final IdentityPropertiesFilter identityPropertiesFilter, final IdentityRepository identityRepository) {
        this.getIdentityMethodResponseBuilderPort = getIdentityMethodResponseBuilderPort;
        this.accountStateRepository = accountStateRepository;
        this.getReferenceIdsResolver = getReferenceIdsResolver;
        this.identityRepository = identityRepository;
        this.identityPropertiesFilter = identityPropertiesFilter;
    }

    @Override
    public GetIdentityMethodResponsePort call(final GetIdentityMethodCallPort getIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {

        final String accountId = getIdentityMethodCallPort.accountId();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        final String[] identityIds = getReferenceIdsResolver.resolve(getIdentityMethodCallPort, previousResponses);

        final RetrivedEntity<IdentityPort> retrivedIdentities = identityIds != null
                ? identityRepository.retrive(identityIds)
                : identityRepository.retriveAll(accountId);

        final IdentityPort[] identityFiltred = identityPropertiesFilter.filter(retrivedIdentities.found(),
                getIdentityMethodCallPort.getProperties());

        return getIdentityMethodResponseBuilderPort
                .reset()
                .list(identityFiltred)
                .notFound(retrivedIdentities.notFound())
                .state(accountState.identityState())
                .build();
    }
}
