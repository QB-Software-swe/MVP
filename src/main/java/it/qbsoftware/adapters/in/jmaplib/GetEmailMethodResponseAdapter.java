package it.qbsoftware.adapters.in.jmaplib;

import it.qbsoftware.business.ports.in.jmap.GetEmailMethodResponsePort;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;

public class GetEmailMethodResponseAdapter implements GetEmailMethodResponsePort{
    GetEmailMethodResponse getEmailMethodResponse;

    public GetEmailMethodResponseAdapter(GetEmailMethodResponse getEmailMethodResponse){
        this.getEmailMethodResponse = getEmailMethodResponse;
    }

}
