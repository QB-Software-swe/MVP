package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.entity.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.EmailSubmissionPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.response.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;

public class GetEmailSubmissionMethodCallService implements GetEmailSubmissionMethodCallUsecase {
    final AccountStateRepository accountStateRepository;
    final GetEmailSubmissionMethodResponseBuilderPort getEmailSubmissionMethodResponseBuilderPort;
    final GetReferenceIdsResolver getReferenceIdsResolver;
    final EmailSubmissionRepository emailSubmissionRepository;
    final EmailSubmissionPropertiesFilter emailSubmissionPropertiesFilter;
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;

    public GetEmailSubmissionMethodCallService(final AccountStateRepository accountStateRepository,
            final EmailSubmissionPropertiesFilter emailSubmissionPropertiesFilter,
            final EmailSubmissionRepository emailSubmissionRepository,
            final GetEmailSubmissionMethodResponseBuilderPort getEmailSubmissionMethodResponseBuilderPort,
            final GetReferenceIdsResolver getReferenceIdsResolver,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort) {
        this.accountStateRepository = accountStateRepository;
        this.getEmailSubmissionMethodResponseBuilderPort = getEmailSubmissionMethodResponseBuilderPort;
        this.getReferenceIdsResolver = getReferenceIdsResolver;
        this.emailSubmissionRepository = emailSubmissionRepository;
        this.emailSubmissionPropertiesFilter = emailSubmissionPropertiesFilter;
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
    }

    @Override
    public MethodResponsePort[] call(final GetEmailSubmissionMethodCallPort getEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        try {
            final String accountId = getEmailSubmissionMethodCallPort.accountId();
            final AccountState accountState = accountStateRepository.retrive(accountId);

            final String[] emailSubmissionIds = getReferenceIdsResolver.resolve(getEmailSubmissionMethodCallPort,
                    previousResponses);

            final RetrivedEntity<EmailSubmissionPort> emailSubmissionRetrived = emailSubmissionIds != null
                    ? emailSubmissionRepository.retrive(emailSubmissionIds)
                    : emailSubmissionRepository.retriveAll(accountId);

            final EmailSubmissionPort[] emailSubmissionFiltred = emailSubmissionPropertiesFilter
                    .filter(emailSubmissionRetrived.found(), emailSubmissionIds);

            return new MethodResponsePort[] {
                    getEmailSubmissionMethodResponseBuilderPort
                            .reset()
                            .list(emailSubmissionFiltred)
                            .notFound(emailSubmissionRetrived.notFound())
                            .state(accountState.emailSubmissionState())
                            .build()
            };

        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
        } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
            return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
        } catch (final InvalidArgumentsException invalidArgumentsException) {
            return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
        }
    }
}
