package it.qbsoftware.business.ports.in.jmap.entity;

public interface MailboxPort {

    public String getId();

    public String getName();

    public String getParentId();

    public RolePort getRole();

    public Long getSortOrder();

    public Long getTotalEmails();

    public Long getUnreadEmails();

    public Long getTotalThreads();

    public Long getUnreadThreads();

    public MailboxRightsPort getMyRights();

    public Boolean getIsSubscribed();

    public MailboxBuilderPort getBuilder();
}
