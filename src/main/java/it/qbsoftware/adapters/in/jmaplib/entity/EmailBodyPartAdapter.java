package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import rs.ltt.jmap.common.entity.EmailBodyPart;

public class EmailBodyPartAdapter implements EmailBodyPartPort {
    private EmailBodyPart emailBodyPart;

    public EmailBodyPartAdapter(final EmailBodyPart emailBodyPart) {
        this.emailBodyPart = emailBodyPart;
    }

    @Override
    public String getPartId() {
        return emailBodyPart.getPartId();
    }

    @Override
    public String getCharset() {
        return emailBodyPart.getCharset();
    }

    @Override
    public String getType() {
        return emailBodyPart.getType();
    }

    @Override
    public String getName() {
        return emailBodyPart.getName();
    }

    @Override
    public Long getSize() {
        return emailBodyPart.getSize();
    }

    public EmailBodyPart adaptee() {
        return emailBodyPart;
    }

    @Override
    public EmailBodyPartBuilderPort toBuilder() {
        return new EmailBodyPartBuilderAdapter();
    }
}
