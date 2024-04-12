package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import rs.ltt.jmap.common.entity.Mailbox;

public class MailboxAdapter implements MailboxPort{
    Mailbox mailbox;

    public MailboxAdapter(Mailbox mailbox){
        this.mailbox = mailbox;
    }

    @Override
    public String getName() {
        return mailbox.getName();
    }

    @Override
    public RolePort getRole() {
        return new RoleAdapter(mailbox.getRole());
    }

}
