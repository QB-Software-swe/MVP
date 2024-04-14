package it.qbsoftware.business.ports.in.jmap.entity;

public interface IdentityPort {

    public String getId();

    public String getName();

    public String getEmail();

    public EmailAddressPort[] getReplyTo();

    public EmailAddressPort[] getBcc();

    public String getTextSignature();

    public String getHtmlSignature();

    public Boolean getMayDelete();
}
