package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailHeaderPort;
import rs.ltt.jmap.common.entity.EmailHeader;

public class EmailHeaderAdapter implements EmailHeaderPort {
    private EmailHeader emailHeader;

    public EmailHeaderAdapter(final EmailHeader emailHeader) {
        this.emailHeader = emailHeader;
    }

    public EmailHeader adaptee() {
        return emailHeader;
    }
    ;
}
