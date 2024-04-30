package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.EmailSubmissionPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;

public class GetEmailSubmissionMethodCallService implements GetEmailSubmissionMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final GetEmailSubmissionMethodResponseBuilderPort getEmailSubmissionMethodResponseBuilderPort;
    private final GetReferenceIdsResolver getReferenceIdsResolver;
    private final EmailSubmissionRepository emailSubmissionRepository;
    private final EmailSubmissionPropertiesFilter emailSubmissionPropertiesFilter;;

    public GetEmailSubmissionMethodCallService(final AccountStateRepository accountStateRepository,
            final EmailSubmissionPropertiesFilter emailSubmissionPropertiesFilter,
            final EmailSubmissionRepository emailSubmissionRepository,
            final GetEmailSubmissionMethodResponseBuilderPort getEmailSubmissionMethodResponseBuilderPort,
            final GetReferenceIdsResolver getReferenceIdsResolver) {
        this.accountStateRepository = accountStateRepository;
        this.getEmailSubmissionMethodResponseBuilderPort = getEmailSubmissionMethodResponseBuilderPort;
        this.getReferenceIdsResolver = getReferenceIdsResolver;
        this.emailSubmissionRepository = emailSubmissionRepository;
        this.emailSubmissionPropertiesFilter = emailSubmissionPropertiesFilter;
    }

    @Override
    public GetEmailSubmissionMethodResponsePort call(
            final GetEmailSubmissionMethodCallPort getEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidResultReferenceExecption, AccountNotFoundException, InvalidArgumentsException {

        final String accountId = getEmailSubmissionMethodCallPort.accountId();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        final String[] emailSubmissionIds = getReferenceIdsResolver.resolve(getEmailSubmissionMethodCallPort,
                previousResponses);

        final RetrivedEntity<EmailSubmissionPort> emailSubmissionRetrived = emailSubmissionIds != null
                ? emailSubmissionRepository.retrive(emailSubmissionIds)
                : emailSubmissionRepository.retriveAll(accountId);

        final EmailSubmissionPort[] emailSubmissionFiltred = emailSubmissionPropertiesFilter
                .filter(emailSubmissionRetrived.found(), getEmailSubmissionMethodCallPort.getProperties());

        return getEmailSubmissionMethodResponseBuilderPort
                .reset()
                .list(emailSubmissionFiltred)
                .notFound(emailSubmissionRetrived.notFound())
                .state(accountState.state())
                .build();
    }
}
