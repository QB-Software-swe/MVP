package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;

public class GetIdentityMethodResponseAdapter implements GetIdentityMethodResponsePort {
    private final GetIdentityMethodResponse getIdentityMethodResponse;

    public GetIdentityMethodResponseAdapter(final GetIdentityMethodResponse getIdentityMethodResponse) {
        this.getIdentityMethodResponse = getIdentityMethodResponse;
    }

    public GetIdentityMethodResponse adaptee() {
        return this.getIdentityMethodResponse;
    }
}
