package it.qbsoftware.adapters.in.jmaplib;

import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;

public class GetIdentityMethodResponseAdapter implements it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort{
    GetIdentityMethodResponse getIdentityMethodResponse;

    public GetIdentityMethodResponseAdapter(GetIdentityMethodResponse getIdentityMethodResponse){
        this.getIdentityMethodResponse = getIdentityMethodResponse;
    }

}
