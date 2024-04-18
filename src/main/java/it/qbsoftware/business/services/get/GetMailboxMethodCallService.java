package it.qbsoftware.business.services.get;

import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.response.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class GetMailboxMethodCallService implements GetMailboxMethodCallUsecase {
        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort;
        final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
        final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
        final AccountStateRepository accountStateRepository;
        final GetReferenceIdsResolver getReferenceIdsResolver;
        final MailboxRepository mailboxRepository;
        final MailboxPropertiesFilter mailboxPropertiesFilter;

        @Inject
        public GetMailboxMethodCallService(final AccountStateRepository accountStateRepository,
                        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
                        final GetReferenceIdsResolver getReferenceIdsResolver,
                        final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
                        final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
                        final MailboxPropertiesFilter mailboxPropertiesFilter,
                        final MailboxRepository mailboxRepository) {
                this.getMailboxMethodResponseBuilderPort = getMailboxMethodResponseBuilderPort;
                this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
                this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
                this.accountStateRepository = accountStateRepository;
                this.getReferenceIdsResolver = getReferenceIdsResolver;
                this.mailboxRepository = mailboxRepository;
                this.mailboxPropertiesFilter = mailboxPropertiesFilter;

        }

        @Override
        public MethodResponsePort[] call(final GetMailboxMethodCallPort getMailboxMethodCallPort,
                        final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
                try {
                        final String accountId = getMailboxMethodCallPort.accountId();
                        AccountState accountState = accountStateRepository.retrive(accountId);

                        final String[] mailboxIds = getReferenceIdsResolver.resolve(getMailboxMethodCallPort,
                                        previousResponses);

                        final RetrivedEntity<MailboxPort> maibloxesRetrived = mailboxIds != null
                                        ? mailboxRepository.retrive(mailboxIds)
                                        : mailboxRepository.retriveAll(accountId);

                        final MailboxPort[] mailboxesFiltred = mailboxPropertiesFilter.filter(maibloxesRetrived.found(),
                                        mailboxIds);

                        return new MethodResponsePort[] {
                                        getMailboxMethodResponseBuilderPort
                                                        .reset()
                                                        .list(mailboxesFiltred)
                                                        .notFound(maibloxesRetrived.notFound())
                                                        .state(accountState.mailboxState()).build()
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
