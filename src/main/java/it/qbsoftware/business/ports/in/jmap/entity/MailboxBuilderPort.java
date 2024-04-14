package it.qbsoftware.business.ports.in.jmap.entity;

public interface MailboxBuilderPort {

    public MailboxBuilderPort id(String id);

    public MailboxBuilderPort name(String name);

    public MailboxBuilderPort parentId(String parentId);

    public MailboxBuilderPort role(RolePort role);

    public MailboxBuilderPort sortOrder(Long sortOrder);

    public MailboxBuilderPort totalEmails(Long totalEmails);

    public MailboxBuilderPort totalThreads(Long totalThreads);

    public MailboxBuilderPort unreadEmails(Long unreadEmails);

    public MailboxBuilderPort unreadThreads(Long unreadThreads);

    public MailboxBuilderPort myRights(MailboxRightsPort mailboxRightsPort);

    public MailboxBuilderPort getIsSubscribed(Boolean isSubscribed);

    public MailboxPort build();

    public MailboxBuilderPort reset();
}
