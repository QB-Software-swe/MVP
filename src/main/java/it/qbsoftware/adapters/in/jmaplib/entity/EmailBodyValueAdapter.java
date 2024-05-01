package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import rs.ltt.jmap.common.entity.EmailBodyValue;

public class EmailBodyValueAdapter implements EmailBodyValuePort {
    private EmailBodyValue emailBodyValue;

    public EmailBodyValueAdapter(final EmailBodyValue emailBodyValue) {
        this.emailBodyValue = emailBodyValue;
    }

    public EmailBodyValue adaptee() {
        return emailBodyValue;
    }
}
