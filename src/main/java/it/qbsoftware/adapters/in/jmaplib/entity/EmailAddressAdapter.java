package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import rs.ltt.jmap.common.entity.EmailAddress;

public class EmailAddressAdapter implements EmailAddressPort {
    private EmailAddress emailAddress;

    public EmailAddressAdapter(final EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EmailAddress adaptee() {
        return emailAddress;
    }
}
