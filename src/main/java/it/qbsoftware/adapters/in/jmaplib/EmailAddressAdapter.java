package it.qbsoftware.adapters.in.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import rs.ltt.jmap.common.entity.EmailAddress;

public class EmailAddressAdapter implements EmailAddressPort {
    EmailAddress emailAddress;

    public EmailAddressAdapter(final EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EmailAddress emailAddress() {
        return emailAddress();
    }
}
