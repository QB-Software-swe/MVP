package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxRightsPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.Mailbox.MailboxBuilder;

public class MailboxBuilderAdapter implements MailboxBuilderPort {
    MailboxBuilder mailboxBuilder;

    public MailboxBuilderAdapter() {
        this.mailboxBuilder = Mailbox.builder();
    }

    @Override
    public MailboxBuilderPort id(String id) {
        mailboxBuilder.id(id);
        return this;
    }

    @Override
    public MailboxBuilderPort name(String name) {
        mailboxBuilder.name(name);
        return this;
    }

    @Override
    public MailboxBuilderPort role(RolePort role) {
        RoleAdapter roleAdapter = (RoleAdapter) role;
        mailboxBuilder.role(roleAdapter.getRole());
        return this;
    }

    @Override
    public MailboxBuilderPort totalEmails(Long totalEmails) {
        mailboxBuilder.totalEmails(totalEmails);
        return this;
    }

    @Override
    public MailboxBuilderPort totalThreads(Long totalThreads) {
        mailboxBuilder.totalThreads(totalThreads);
        return this;
    }

    @Override
    public MailboxBuilderPort unreadEmails(Long unreadEmails) {
        mailboxBuilder.unreadEmails(unreadEmails);
        return this;
    }

    @Override
    public MailboxBuilderPort unreadThreads(Long unreadThreads) {
        mailboxBuilder.unreadThreads(unreadThreads);
        return this;
    }

    @Override
    public MailboxBuilderPort parentId(String parentId) {
        this.mailboxBuilder.parentId(parentId);
        return this;
    }

    @Override
    public MailboxBuilderPort sortOrder(Long sortOrder) {
        this.mailboxBuilder.sortOrder(sortOrder);
        return this;
    }

    @Override
    public MailboxBuilderPort myRights(MailboxRightsPort mailboxRightsPort) {
        this.mailboxBuilder.myRights(((MailboxRightsAdapter) mailboxRightsPort).adaptee());
        return this;
    }

    @Override
    public MailboxBuilderPort isSubscribed(Boolean isSubscribed) {
        this.mailboxBuilder.isSubscribed(isSubscribed);
        return this;
    }

    @Override
    public MailboxPort build() {
        return new MailboxAdapter(mailboxBuilder.build());
    }

    @Override
    public MailboxBuilderPort reset() {
        this.mailboxBuilder = Mailbox.builder();
        return this;
    }

}
