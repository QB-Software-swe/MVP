package it.qbsoftware.business.services.set;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import it.qbsoftware.business.domain.MailboxInfo;
import it.qbsoftware.business.domain.MailboxInfoConvertToMailboxPort;
import it.qbsoftware.business.domain.MailboxPatcher;
import it.qbsoftware.business.ports.in.guava.CaseFormatPort;
import it.qbsoftware.business.ports.in.guava.SplitterPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePortBuilder;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.jmap.MailboxInfoRepository;

public class SetMailboxMethodCallService implements SetMailboxMethodCallUsecase {

    final SetMailboxMethodResponsePortBuilder setMailboxMethodResponseBuilder;
    final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort;
    final MailboxBuilderPort mailboxBuilderPort;
    final MailboxInfoRepository mailboxInfoRepository;
    final SetErrorPort setErrorPort;
    final SplitterPort splitterPort;
    final CaseFormatPort caseFormatPort;
    final RolePort rolePort;

    public SetMailboxMethodCallService(final SetMailboxMethodResponsePortBuilder setMailboxMethodResponseBuilder,
            final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort,
            final MailboxBuilderPort mailboxBuilderPort, MailboxInfoRepository mailboxInfoRepository,
            final SetErrorPort setErrorPort, final SplitterPort splitterPort, final CaseFormatPort caseFormatPort,
            final RolePort rolePort) {
        this.setMailboxMethodResponseBuilder = setMailboxMethodResponseBuilder;
        this.stateMismatchMethodErrorResponsePort = stateMismatchMethodErrorResponsePort;
        this.mailboxBuilderPort = mailboxBuilderPort;
        this.mailboxInfoRepository = mailboxInfoRepository;
        this.setErrorPort = setErrorPort;
        this.splitterPort = splitterPort;
        this.caseFormatPort = caseFormatPort;
        this.rolePort = rolePort;
    }

    @Override
    public MethodResponsePort[] call(SetMailboxMethodCallPort setMailboxMethodCallPort) {
        final Map<String, MailboxPort> mapCreateMailbox = setMailboxMethodCallPort.getCreate();
        final Map<String, Map<String, Object>> mapUpdateObject = setMailboxMethodCallPort.getUpdate();

        /* 
        if (ifInStateMismatch(setMailboxMethodCallPort.ifInState())) {
            return new MethodResponsePort[] { stateMismatchMethodErrorResponsePort };
        }

        if (mapCreateMailbox != null && mapCreateMailbox.size() > 0) {
            processCreateMailbox(setMailboxMethodCallPort.accountId(), mapCreateMailbox);
        }
        if (mapUpdateObject != null && mapUpdateObject.size() > 0) {
            processUpdateMailbox(setMailboxMethodCallPort.accountId(), mapUpdateObject);
        }

        final SetMailboxMethodResponsePort setMailboxMethodResponsePort = setMailboxMethodResponseBuilder.build();
        AccountUpdate accountUpdate = new AccountUpdate(setMailboxMethodCallPort.accountId());
        accountUpdate.put("NULLSTATE", null); // TODO: tenere traccia degli update
        // Generate response
        */
        return new MethodResponsePort[] {  };
    }

    private boolean ifInStateMismatch(final String ifInState) {
        return ifInState != null && ifInState.equals("0"); // TODO: check effettivo dello stato salvato nel database
                                                           // degli oggetti Mailbox per l'account target
    }

    // FIXME: separare???
    private void processCreateMailbox(final String accountId, final Map<String, MailboxPort> mapCreateMailbox) {

        for (Map.Entry<String, MailboxPort> entry : mapCreateMailbox.entrySet()) {
            final String createId = entry.getKey();
            final MailboxPort mailbox = entry.getValue();
            final String name = mailbox.getName();

            if (duplicateMailbox(mailboxInfoRepository.retriveAll(accountId), name)) {
                SetErrorPort copySetErrorPort = setErrorPort;
                copySetErrorPort.invalidPropertiesErorr("Mailbox '" + name + "' already exits");
                setMailboxMethodResponseBuilder.notCreated(createId, copySetErrorPort);
            }

            // FIXME: questo può creare conflitti meglio salvare l'ultimo id usato per
            // creare la mailbox e aggiungere uno
            final String id = UUID.randomUUID().toString();
            final MailboxInfo mailboxInfo = new MailboxInfo(id, name, mailbox.getRole());
            setMailboxMethodResponseBuilder.created(createId,
                    new MailboxInfoConvertToMailboxPort(mailboxBuilderPort).convert(mailboxInfo));
        }
    }

    // FIXME: separare2???
    private boolean duplicateMailbox(MailboxInfo[] mailboxInfos, String newMailboxName) {
        for (MailboxInfo mailboxInfo : mailboxInfos) {
            if (newMailboxName.equals(mailboxInfo.name())) {
                return true;
            }
        }

        return false;
    }

    private void processUpdateMailbox(
            String accountId,
            Map<String, Map<String, Object>> update) {

        for (final Map.Entry<String, Map<String, Object>> entry : update.entrySet()) {
            final String id = entry.getKey();

            // getmailbox (done) -> patch mailbox (done) -> save mailbox (done)
            Optional<MailboxInfo> currentMailboxInfo = mailboxInfoRepository.retrive(accountId, id);
            MailboxPatcher mailboxPatcher = new MailboxPatcher(splitterPort, caseFormatPort, rolePort);

            // FIXME: ugly asf
            if (!currentMailboxInfo.isPresent()) {
                SetErrorPort copySetErrorPort = setErrorPort;
                copySetErrorPort.notFoundError("Mailbox id: " + id + " not found");
                setMailboxMethodResponseBuilder.notUpdated(
                        id, copySetErrorPort);
            } else {
                try {
                    final MailboxInfo modifiedMailboxInfo = mailboxPatcher.patch(accountId, currentMailboxInfo.get(),
                            entry.getValue());
                    mailboxInfoRepository.save(accountId, modifiedMailboxInfo);
                } catch (Exception e) {
                    SetErrorPort copySetErrorPort = setErrorPort;
                    copySetErrorPort.invalidPropertiesErorr(e.getMessage());
                    setMailboxMethodResponseBuilder.notUpdated(
                            id, copySetErrorPort);
                }
            }
        }

    }
}
