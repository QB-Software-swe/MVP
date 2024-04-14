package it.qbsoftware.business.services.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.qbsoftware.business.domain.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.MailboxInfo;
import it.qbsoftware.business.domain.MailboxInfoConvertToMailboxPort;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.util.CommonMethodCallSetup;
import it.qbsoftware.business.domain.util.MethodCallSetup;
import it.qbsoftware.business.domain.util.get.GetEntityPropertiesFilter;
import it.qbsoftware.business.domain.util.get.GetMailboxPropertiesFilter;
import it.qbsoftware.business.domain.util.get.GetMethodCallSetup;
import it.qbsoftware.business.domain.util.get.GetMethodCallSetupImp;
import it.qbsoftware.business.domain.util.get.GetRetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxInfoRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class GetMailboxMethodCallService implements GetMailboxMethodCallUsecase {
        final MailboxBuilderPort mailboxBuilderPort;
        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort;
        final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;

        final CommonMethodCallSetup commonMethodCallSetup;
        final GetMethodCallSetup<MailboxPort> getMethodCallSetup;

        public GetMailboxMethodCallService(final MailboxRepository mailboxRepository,
                        final MailboxBuilderPort mailboxBuilderPort,
                        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
                        final AccountStateRepository accountStateRepository,
                        final ResultReferenceResolverPort resultReferenceResolverPort,
                        final InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort) {
                this.mailboxBuilderPort = mailboxBuilderPort;
                this.getMailboxMethodResponseBuilderPort = getMailboxMethodResponseBuilderPort;
                this.invalidResultReferenceMethodErrorResponsePort = invalidResultReferenceMethodErrorResponsePort;

                this.commonMethodCallSetup = new MethodCallSetup(accountStateRepository);
                this.getMethodCallSetup = new GetMethodCallSetupImp<MailboxPort>(resultReferenceResolverPort,
                                mailboxRepository);
        }

        @Override
        public MethodResponsePort[] call(final GetMailboxMethodCallPort getMailboxMethodCallPort,
                        final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

                AccountState accountState;
                try {
                        accountState = commonMethodCallSetup.accountState(getMailboxMethodCallPort.accountId());
                } catch (final AccountNotFoundException accountNotFoundException) {
                        return new MethodResponsePort[] { new AccountNotFoundMethodErrorResponse() };
                }

                final GetRetrivedEntity<MailboxPort> getRetrivedMailboxes;
                try {
                        getRetrivedMailboxes = getMethodCallSetup.getEntity(getMailboxMethodCallPort,
                                        previousResponses);
                } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                        return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
                }

                final MailboxPort[] mailboxesFiltred;
                final GetEntityPropertiesFilter<MailboxPort> getMailboxPropertiesFilter = new GetMailboxPropertiesFilter(
                                mailboxBuilderPort.reset());
                try {
                        mailboxesFiltred = getMailboxPropertiesFilter.filter(getRetrivedMailboxes.found(),
                                        getMailboxMethodCallPort.getProperties());
                } catch (final InvalidArgumentsException invalidArgumentsException) {
                        return new MethodResponsePort[] { invalidResultReferenceMethodErrorResponsePort };
                }

                return new MethodResponsePort[] {
                                getMailboxMethodResponseBuilderPort
                                                .reset()
                                                .list(mailboxesFiltred)
                                                .notFound(getRetrivedMailboxes.notFound())
                                                .state(accountState.mailboxState()).build()
                };
        }

}
