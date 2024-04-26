package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponsePort;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse;

public class ChangesIdentityMethodResponseAdapter implements ChangesIdentityMethodResponsePort {
    private final ChangesIdentityMethodResponse changesIdentityMethodResponse;

    public ChangesIdentityMethodResponseAdapter(final ChangesIdentityMethodResponse changesIdentityMethodResponse) {
        this.changesIdentityMethodResponse = changesIdentityMethodResponse;
    }

    public ChangesIdentityMethodResponse adaptee() {
        return changesIdentityMethodResponse;
    }
}
