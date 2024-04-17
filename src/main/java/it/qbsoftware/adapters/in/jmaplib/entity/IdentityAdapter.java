package it.qbsoftware.adapters.in.jmaplib.entity;

import java.util.stream.Collectors;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import rs.ltt.jmap.common.entity.Identity;

public class IdentityAdapter implements IdentityPort {
    private Identity identity;

    public IdentityAdapter(Identity identity) {
        this.identity = identity;
    }

    @Override
    public String getId() {
        return this.identity.getId();
    }

    @Override
    public String getName() {
        return this.identity.getName();
    }

    @Override
    public String getEmail() {
        return this.identity.getEmail();
    }

    @Override
    public EmailAddressPort[] getReplyTo() {
        return this.identity.getReplyTo().stream().map(emailAddress -> new EmailAddressAdapter(emailAddress))
                .collect(Collectors.toList()).toArray(EmailAddressAdapter[]::new);
    }

    @Override
    public EmailAddressPort[] getBcc() {
        return this.identity.getBcc().stream().map(emailAddress -> new EmailAddressAdapter(emailAddress))
                .collect(Collectors.toList()).toArray(EmailAddressAdapter[]::new);
    }

    @Override
    public String getTextSignature() {
        return this.identity.getTextSignature();
    }

    @Override
    public String getHtmlSignature() {
        return this.identity.getHtmlSignature();
    }

    @Override
    public Boolean getMayDelete() {
        return this.identity.getMayDelete();
    }

    public Identity adaptee() {return this.identity;}
}
