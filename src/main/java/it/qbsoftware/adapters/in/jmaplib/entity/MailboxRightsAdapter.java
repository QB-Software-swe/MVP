package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxRightsPort;
import rs.ltt.jmap.common.entity.MailboxRights;

public class MailboxRightsAdapter implements MailboxRightsPort {
    MailboxRights mailboxRights;

    public MailboxRightsAdapter(MailboxRights mailboxRights) {
        this.mailboxRights = mailboxRights;
    }

    public MailboxRights adaptee() {
        return mailboxRights;
    }
}