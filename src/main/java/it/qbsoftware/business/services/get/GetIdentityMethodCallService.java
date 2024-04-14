package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.util.CommonMethodCallSetup;
import it.qbsoftware.business.domain.util.MethodCallSetup;
import it.qbsoftware.business.domain.util.get.GetEntityPropertiesFilter;
import it.qbsoftware.business.domain.util.get.GetIdentityPropertiesFilter;
import it.qbsoftware.business.domain.util.get.GetMethodCallSetup;
import it.qbsoftware.business.domain.util.get.GetMethodCallSetupImp;
import it.qbsoftware.business.domain.util.get.GetRetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.GetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;

public class GetIdentityMethodCallService implements GetIdentityMethodCallUsecase {
    final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort;
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
    final CommonMethodCallSetup commonMethodCallSetup;
    final GetMethodCallSetup<IdentityPort> getMethodCallSetup;
    final GetEntityPropertiesFilter<IdentityPort> getIdentityPropertiesFilter;

    public GetIdentityMethodCallService(final IdentityRepository identityRepository,
            final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort,
            final AccountStateRepository accountStateRepository,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
            final ResultReferenceResolverPort resultReferenceResolverPort,
            final IdentityBuilderPort identityBuilderPort,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort) {
        this.getIdentityMethodResponseBuilderPort = getIdentityMethodResponseBuilderPort;
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;

        this.commonMethodCallSetup = new MethodCallSetup(accountStateRepository);
        this.getMethodCallSetup = new GetMethodCallSetupImp<IdentityPort>(resultReferenceResolverPort,
                identityRepository);
        this.getIdentityPropertiesFilter = new GetIdentityPropertiesFilter(identityBuilderPort);
    }

    @Override
    public MethodResponsePort[] call(final GetIdentityMethodCallPort getIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        AccountState accountState;
        try {
            accountState = commonMethodCallSetup.accountState(getIdentityMethodCallPort.accountId());
        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
        }

        GetRetrivedEntity<IdentityPort> getRetrivedIdentities;
        try {
            getRetrivedIdentities = getMethodCallSetup.getEntity(getIdentityMethodCallPort, previousResponses);
        } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
            return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
        }

        final IdentityPort[] identitiesFound;
        try {
            identitiesFound = getIdentityPropertiesFilter.filter(getRetrivedIdentities.found(),
                    getIdentityMethodCallPort.getProperties());
        } catch (final InvalidArgumentsException invalidArgumentsException) {
            return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
        }

        return new MethodResponsePort[] {
                getIdentityMethodResponseBuilderPort
                        .reset()
                        .list(identitiesFound)
                        .notFound(getRetrivedIdentities.notFound())
                        .state(accountState.identityState())
                        .build()
        };
    }
}
