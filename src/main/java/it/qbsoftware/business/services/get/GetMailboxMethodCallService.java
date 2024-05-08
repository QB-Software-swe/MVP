package it.qbsoftware.business.services.get;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.ReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

public class GetMailboxMethodCallService implements GetMailboxMethodCallUsecase {
    private final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort;
    private final AccountStateRepository accountStateRepository;
    private final ReferenceIdsResolver getReferenceIdsResolver;
    private final MailboxRepository mailboxRepository;
    private final MailboxPropertiesFilter mailboxPropertiesFilter;
    ;

    public GetMailboxMethodCallService(
            final AccountStateRepository accountStateRepository,
            final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
            final ReferenceIdsResolver getReferenceIdsResolver,
            final MailboxPropertiesFilter mailboxPropertiesFilter,
            final MailboxRepository mailboxRepository) {
        this.getMailboxMethodResponseBuilderPort = getMailboxMethodResponseBuilderPort;
        this.accountStateRepository = accountStateRepository;
        this.getReferenceIdsResolver = getReferenceIdsResolver;
        this.mailboxRepository = mailboxRepository;
        this.mailboxPropertiesFilter = mailboxPropertiesFilter;
    }

    @Override
    public GetMailboxMethodResponsePort call(
            final GetMailboxMethodCallPort getMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws InvalidArgumentsException,
                    AccountNotFoundException,
                    InvalidResultReferenceExecption {

        final String accountId = getMailboxMethodCallPort.accountId();
        final AccountState accountState = accountStateRepository.retrive(accountId);

        final String[] mailboxIds =
                getReferenceIdsResolver.resolve(getMailboxMethodCallPort, previousResponses);

        final RetrivedEntity<MailboxPort> maibloxesRetrived =
                mailboxIds != null
                        ? mailboxRepository.retrive(mailboxIds)
                        : mailboxRepository.retriveAll(accountId);

        final MailboxPort[] mailboxesFiltred =
                mailboxPropertiesFilter.filter(
                        maibloxesRetrived.found(), getMailboxMethodCallPort.getProperties());

        return getMailboxMethodResponseBuilderPort
                .reset()
                .list(mailboxesFiltred)
                .notFound(maibloxesRetrived.notFound())
                .state(accountState.state())
                .build();
    }
}
