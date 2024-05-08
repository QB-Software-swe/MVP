package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxRightsPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import rs.ltt.jmap.common.entity.Mailbox;

public class MailboxAdapter implements MailboxPort {
    private Mailbox mailbox;

    public MailboxAdapter(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    @Override
    public String getName() {
        return mailbox.getName();
    }

    @Override
    public RolePort getRole() {
        var mailboxRole = mailbox.getRole();
        return mailboxRole != null ? new RoleAdapter(mailboxRole) : null;
    }

    @Override
    public String getId() {
        return this.mailbox.getId();
    }

    @Override
    public String getParentId() {
        return this.mailbox.getParentId();
    }

    @Override
    public Long getSortOrder() {
        return this.mailbox.getSortOrder();
    }

    @Override
    public Long getTotalEmails() {
        return this.mailbox.getTotalEmails();
    }

    @Override
    public Long getUnreadEmails() {
        return this.mailbox.getUnreadEmails();
    }

    @Override
    public Long getTotalThreads() {
        return this.mailbox.getTotalThreads();
    }

    @Override
    public Long getUnreadThreads() {
        return this.mailbox.getUnreadEmails();
    }

    @Override
    public MailboxRightsPort getMyRights() {
        return new MailboxRightsAdapter(this.mailbox.getMyRights());
    }

    @Override
    public Boolean getIsSubscribed() {
        return this.mailbox.getIsSubscribed();
    }

    public Mailbox adaptee() {
        return this.mailbox;
    }

    @Override
    public MailboxBuilderPort toBuilder() {
        return new MailboxBuilderAdapter(
                Mailbox.builder()
                        .id(mailbox.getId())
                        .role(mailbox.getRole())
                        .name(mailbox.getName())
                        .parentId(mailbox.getParentId())
                        .sortOrder(mailbox.getSortOrder())
                        .totalEmails(mailbox.getTotalEmails())
                        .unreadEmails(mailbox.getUnreadEmails())
                        .totalThreads(mailbox.getTotalThreads())
                        .unreadThreads(mailbox.getUnreadThreads())
                        .myRights(mailbox.getMyRights())
                        .isSubscribed(mailbox.getIsSubscribed()));
    }
}
