package it.qbsoftware.business.services;

import it.qbsoftware.business.domain.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.ports.in.jmap.GetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetIdentityMethodCallService implements GetIdentityMethodCallUsecase {
    final IdentityRepository identityRepository;
    final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort;
    final AccountStateRepository accountStateRepository;
    final ResultReferenceResolverPort resultReferenceResolverPort;
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;

    public GetIdentityMethodCallService(final IdentityRepository identityRepository,
            final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort,
            final AccountStateRepository accountStateRepository,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
            final ResultReferenceResolverPort resultReferenceResolverPort) {
        this.identityRepository = identityRepository;
        this.getIdentityMethodResponseBuilderPort = getIdentityMethodResponseBuilderPort;
        this.accountStateRepository = accountStateRepository;
        this.resultReferenceResolverPort = resultReferenceResolverPort;
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final GetIdentityMethodCallPort getIdentityMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        final String accountId = getIdentityMethodCallPort.accountId();
        Optional<AccountState> accountState = accountStateRepository.retrive(accountId);
        if (!accountState.isPresent()) {
            return new MethodResponsePort[] {
                    new AccountNotFoundMethodErrorResponse()
            };
        }

        List<String> ids;
        if (getIdentityMethodCallPort.getIdsReference() != null) {
            try {
                ids = Arrays.asList(resultReferenceResolverPort.resolve(getIdentityMethodCallPort.getIdsReference(),
                        previousResponses));
            } catch (IllegalArgumentException exception) {
                return new MethodResponsePort[] {
                        invalidResultReferenceMethodErrorResponsePort
                };
            }
        } else {
            ids = Arrays.asList(getIdentityMethodCallPort.getIds());
        }

        Map<String, IdentityPort> retrivedIdentityPorts = identityRepository.retrive(ids.toArray(String[]::new));
        List<IdentityPort> identityPorts = retrivedIdentityPorts.entrySet().stream().parallel()
                .filter(identityEntrySet -> identityEntrySet.getValue() != null)
                .map(Map.Entry<String, IdentityPort>::getValue)
                .collect(Collectors.toList());
        List<String> identityPortsNotFound = retrivedIdentityPorts.entrySet().stream().parallel()
                .filter(identityEntrySet -> identityEntrySet.getValue() == null)
                .map(Map.Entry<String, IdentityPort>::getKey)
                .collect(Collectors.toList());

        // FIXME: bisognerebbe ritornare solo le properties richieste, in teoria non
        // è obbligatorio, serve solo per recuperare campi specifici e ridure il carico
        // sulla rete

        return new MethodResponsePort[] {
                getIdentityMethodResponseBuilderPort
                        .reset()
                        .list(identityPorts.toArray(IdentityPort[]::new))
                        .notFound(identityPortsNotFound.toArray(String[]::new))
                        .state(accountState.get().identityState())
                        .build()
        };
    }
}
