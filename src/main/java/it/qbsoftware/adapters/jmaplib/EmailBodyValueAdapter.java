package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import rs.ltt.jmap.common.entity.EmailBodyValue;

public class EmailBodyValueAdapter implements EmailBodyValuePort{
    EmailBodyValue emailBodyValue;
    
    public EmailBodyValueAdapter(EmailBodyValue emailBodyValue){
        this.emailBodyValue = emailBodyValue;
    }

}
