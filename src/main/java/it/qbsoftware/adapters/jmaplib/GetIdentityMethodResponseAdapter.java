package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.GetIdentityMethodResponsePort;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;

public class GetIdentityMethodResponseAdapter implements GetIdentityMethodResponsePort{
    GetIdentityMethodResponse getIdentityMethodResponse;

    public GetIdentityMethodResponseAdapter(GetIdentityMethodResponse getIdentityMethodResponse){
        this.getIdentityMethodResponse = getIdentityMethodResponse;
    }

}
