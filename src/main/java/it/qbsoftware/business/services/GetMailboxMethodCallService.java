package it.qbsoftware.business.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.qbsoftware.business.domain.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.MailboxInfo;
import it.qbsoftware.business.domain.MailboxInfoConvertToMailboxPort;
import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResultReferencePort;
import it.qbsoftware.business.ports.in.usecase.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxInfoRepository;

public class GetMailboxMethodCallService implements GetMailboxMethodCallUsecase {
        final MailboxInfoRepository mailboxInfoRepository;
        final MailboxBuilderPort mailboxBuilderPort;
        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort;
        final AccountStateRepository accountStateRepository;

        public GetMailboxMethodCallService(final MailboxInfoRepository mailboxInfoRepository,
                        final MailboxBuilderPort mailboxBuilderPort,
                        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
                        final AccountStateRepository accountStateRepository) {
                this.mailboxInfoRepository = mailboxInfoRepository;
                this.mailboxBuilderPort = mailboxBuilderPort;
                this.getMailboxMethodResponseBuilderPort = getMailboxMethodResponseBuilderPort;
                this.accountStateRepository = accountStateRepository;
        }

        @Override
        public MethodResponsePort[] call(final GetMailboxMethodCallPort getMailboxMethodCallPort,
                        final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {

                final String accountId = getMailboxMethodCallPort.accountId();
                Optional<AccountState> accountState = accountStateRepository.retrive(accountId);
                if (!accountState.isPresent()) {
                        return new MethodResponsePort[] {
                                        new AccountNotFoundMethodErrorResponse()
                        };
                }

                final String[] mailboxIdsToRetrive = getMailboxMethodCallPort.getIds();
                final ResultReferencePort clientIdsReference = getMailboxMethodCallPort.getIdsReference();

                if (clientIdsReference != null) {
                        // TODO: in pratica bisogna risolvere gli id creati dal client con gli effettivi
                        // id degli oggetti creati dal server come richiesto dal client. Per fare questo
                        // bisogna usare previousResponses.
                        // Il problema è che getIdsReference non è un campo standard, ma molto
                        // probabilmente un campo per tenere separati gli id "standard" e gli id "del
                        // client". Il punto è che bisogna capire se è getIdsReference tiene solo quelli
                        // creati dal client oppure anche quelli normali, perché nel primo caso è
                        // neccessario unire sia quelli ottenuti da quest'ultimo, ma sia quelli
                        // standard.
                }

                List<MailboxInfo> retrivedMailboxsInfo = mailboxIdsToRetrive == null
                                ? Arrays.asList(mailboxInfoRepository.retriveAll(accountId))
                                : Arrays.asList(
                                                mailboxInfoRepository.retrive(accountId, mailboxIdsToRetrive));

                MailboxInfoConvertToMailboxPort mailboxInfoConvertToMailboxPort = new MailboxInfoConvertToMailboxPort(
                                null);

                return new MethodResponsePort[] {
                                getMailboxMethodResponseBuilderPort.list(
                                                retrivedMailboxsInfo.stream()
                                                                .map(mailboxInfo -> mailboxInfoConvertToMailboxPort
                                                                                .convert(mailboxInfo))
                                                                .toArray(MailboxPort[]::new))
                                                .state(accountState.get().mailboxState()).build()
                };
        }

}
