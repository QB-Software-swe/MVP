package it.qbsoftware.business.domain;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

//TODO: remove
public class MailboxInfoConvertToMailboxPort {
    MailboxBuilderPort mailboxBuilderPort;

    public MailboxInfoConvertToMailboxPort(MailboxBuilderPort mailboxBuilderPort) {
        this.mailboxBuilderPort = mailboxBuilderPort;
    }

    public MailboxPort convert(final MailboxInfo mailboxInfo) {
        return mailboxBuilderPort
                .id(mailboxInfo.id())
                .name(mailboxInfo.name())
                .role(mailboxInfo.role())
                .totalEmails(null)      //TODO: volendo si potrebbe calcolare, in ogni caso per lo standard non è un problema
                .totalThreads(null)
                .unreadEmails(null)
                .unreadThreads(null)
                .build();
    }
}
