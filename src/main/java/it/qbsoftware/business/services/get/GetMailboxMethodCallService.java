package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class GetMailboxMethodCallService implements GetMailboxMethodCallUsecase {
        private final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort;
        private final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;
        private final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;
        private final AccountStateRepository accountStateRepository;
        private final GetReferenceIdsResolver getReferenceIdsResolver;
        private final MailboxRepository mailboxRepository;
        private final MailboxPropertiesFilter mailboxPropertiesFilter;
        private final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

        public GetMailboxMethodCallService(final AccountStateRepository accountStateRepository,
                        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
                        final GetReferenceIdsResolver getReferenceIdsResolver,
                        final InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort,
                        final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort,
                        final MailboxPropertiesFilter mailboxPropertiesFilter,
                        final MailboxRepository mailboxRepository,
                        AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort) {
                this.getMailboxMethodResponseBuilderPort = getMailboxMethodResponseBuilderPort;
                this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;
                this.invalidArgumentsMethodErrorResponsePort = invalidArgumentsMethodErrorResponsePort;
                this.accountStateRepository = accountStateRepository;
                this.getReferenceIdsResolver = getReferenceIdsResolver;
                this.mailboxRepository = mailboxRepository;
                this.mailboxPropertiesFilter = mailboxPropertiesFilter;
                this.accountNotFoundMethodErrorResponsePort = accountNotFoundMethodErrorResponsePort;

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
                                        getMailboxMethodCallPort.getProperties());

                        return new MethodResponsePort[] {
                                        getMailboxMethodResponseBuilderPort
                                                        .reset()
                                                        .list(mailboxesFiltred)
                                                        .notFound(maibloxesRetrived.notFound())
                                                        .state(accountState.mailboxState()).build()
                        };

                } catch (final AccountNotFoundException accountNotFoundException) {
                        return new MethodResponsePort[] { accountNotFoundMethodErrorResponsePort };
                } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                        return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
                } catch (final InvalidArgumentsException invalidArgumentsException) {
                        return new MethodResponsePort[] { invalidArgumentsMethodErrorResponsePort };
                }
        }

}
