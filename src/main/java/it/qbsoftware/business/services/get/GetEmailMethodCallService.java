package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.util.MethodCallSetup;
import it.qbsoftware.business.domain.util.get.GetEmailPropertiesFilter;
import it.qbsoftware.business.domain.util.get.GetEntityPropertiesFilter;
import it.qbsoftware.business.domain.util.get.GetRetrivedEntity;
import it.qbsoftware.business.domain.util.get.GetMethodCallSetup;
import it.qbsoftware.business.domain.util.get.GetMethodCallSetupImp;
import it.qbsoftware.business.domain.util.CommonMethodCallSetup;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

public class GetEmailMethodCallService implements GetEmailMethodCallUsecase {
    final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
    final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
    final EmailRepository emailRepository;
    final EmailBuilderPort emailBuilderPort;
    final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort;
    final CommonMethodCallSetup commonMethodCallSetup;
    final GetMethodCallSetup<EmailPort> getMethodCallSetup;

    public GetEmailMethodCallService(final EmailRepository emailRepository,
            final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
            final ResultReferenceResolverPort resultReferenceResolverPort,
            final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort,
            final EmailBuilderPort emailBuilderPort,
            final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
            final AccountStateRepository accountStateRepository) {
        this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
        this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
        this.emailRepository = emailRepository;
        this.emailBuilderPort = emailBuilderPort;
        this.getEmailMethodResponseBuilderPort = getEmailMethodResponseBuilderPort;

        this.commonMethodCallSetup = new MethodCallSetup(accountStateRepository);
        this.getMethodCallSetup = new GetMethodCallSetupImp<EmailPort>(
                resultReferenceResolverPort, emailRepository);
    }

    @Override
    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        getEmailMethodResponseBuilderPort.reset();

        AccountState accountState = null;
        try {
            accountState = commonMethodCallSetup.accountState(getEmailMethodCallPort.accountId());
        } catch (final AccountNotFoundException accountNotFoundException) {
            return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
        }

        GetRetrivedEntity<EmailPort> getRetrivedEmails;
        try {
            getRetrivedEmails = getMethodCallSetup.getEntity(getEmailMethodCallPort, previousResponses);
        } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
            return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
        }

        final GetEntityPropertiesFilter<EmailPort> getEmailPropertiesFilter = new GetEmailPropertiesFilter(
                emailBuilderPort.reset(),
                getEmailMethodCallPort.getBodyProperties(), getEmailMethodCallPort.getFetchTextBodyValues(),
                getEmailMethodCallPort.getFetchHTMLBodyValues(), getEmailMethodCallPort.getFetchAllBodyValues(),
                getEmailMethodCallPort.getMaxBodyValueBytes());

        final EmailPort[] emailsFiltred;
        try {
            emailsFiltred = getEmailPropertiesFilter.filter(getRetrivedEmails.found(),
                    getEmailMethodCallPort.getProperties());
        } catch (InvalidArgumentsException invalidArgumentsException) {
            return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
        }

        return new MethodResponsePort[] {
                getEmailMethodResponseBuilderPort
                        .reset()
                        .list(emailsFiltred)
                        .notFound(getRetrivedEmails.notFound())
                        .state(accountState.emailState())
                        .build()
        };
    }

}