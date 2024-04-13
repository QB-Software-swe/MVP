package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.Mailbox.MailboxBuilder;

public class MailboxBuilderAdapter implements MailboxBuilderPort{
    MailboxBuilder mailboxBuilder;

    public MailboxBuilderAdapter(){
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
    public MailboxPort build() {
        return new MailboxAdapter(mailboxBuilder.build());
    }

}
