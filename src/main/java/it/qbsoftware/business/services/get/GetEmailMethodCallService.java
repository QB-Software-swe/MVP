package it.qbsoftware.business.services.get;

import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.EmailFilterBodyPartSettings;
import it.qbsoftware.business.domain.methodcall.filter.EmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.response.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class GetEmailMethodCallService implements GetEmailMethodCallUsecase {
    private final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    private final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
    private final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort;
    private final AccountStateRepository accountStateRepository;
    private final EmailRepository emailRepository;
    private final GetReferenceIdsResolver getReferenceIdsResolver;
    private final EmailPropertiesFilter emailPropertiesFilter;

    @Inject
    public GetEmailMethodCallService(final AccountStateRepository accountStateRepository,
            final EmailPropertiesFilter emailPropertiesFilter, final EmailRepository emailRepository,
            final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort,
            final GetReferenceIdsResolver getReferenceIdsResolver,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort) {
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
        this.getEmailMethodResponseBuilderPort = getEmailMethodResponseBuilderPort;
        this.accountStateRepository = accountStateRepository;
        this.emailRepository = emailRepository;
        this.getReferenceIdsResolver = getReferenceIdsResolver;
        this.emailPropertiesFilter = emailPropertiesFilter;
    }

    @Override
    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

        try {
            final String accountId = getEmailMethodCallPort.accountId();
            final AccountState accountState = accountStateRepository.retrive(accountId);

            final String[] emailIds = getReferenceIdsResolver.resolve(getEmailMethodCallPort, previousResponses);

            final RetrivedEntity<EmailPort> retrivedEmailsResult = emailIds != null
                    ? emailRepository.retrive(emailIds)
                    : emailRepository.retriveAll(accountId);

            final EmailFilterBodyPartSettings emailFilterBodyPartSettings = new EmailFilterBodyPartSettings(
                    getEmailMethodCallPort.getBodyProperties(), getEmailMethodCallPort.getFetchTextBodyValues(),
                    getEmailMethodCallPort.getFetchHtmlBodyValues(), getEmailMethodCallPort.getFetchAllBodyValues(),
                    getEmailMethodCallPort.getMaxBodyValueBytes());

            final EmailPort[] emailsFiltred = emailPropertiesFilter.filter(retrivedEmailsResult.found(),
                    getEmailMethodCallPort.getProperties(), emailFilterBodyPartSettings);

            return new MethodResponsePort[] {
                    getEmailMethodResponseBuilderPort
                            .reset()
                            .list(emailsFiltred)
                            .notFound(retrivedEmailsResult.notFound())
                            .state(accountState.emailState())
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
