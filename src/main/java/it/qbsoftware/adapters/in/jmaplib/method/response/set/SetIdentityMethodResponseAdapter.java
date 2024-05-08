package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponsePort;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse;

public class SetIdentityMethodResponseAdapter implements SetIdentityMethodResponsePort {
    private final SetIdentityMethodResponse setIdentityMethodResponse;

    public SetIdentityMethodResponseAdapter(
            final SetIdentityMethodResponse setIdentityMethodResponse) {
        this.setIdentityMethodResponse = setIdentityMethodResponse;
    }

    public SetIdentityMethodResponse adaptee() {
        return setIdentityMethodResponse;
    }
}
