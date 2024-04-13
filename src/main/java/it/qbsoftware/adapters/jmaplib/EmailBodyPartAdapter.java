package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import rs.ltt.jmap.common.entity.EmailBodyPart;

public class EmailBodyPartAdapter implements EmailBodyPartPort{
    EmailBodyPart emailBodyPart;

    public EmailBodyPartAdapter(EmailBodyPart emailBodyPart){
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

    

}
