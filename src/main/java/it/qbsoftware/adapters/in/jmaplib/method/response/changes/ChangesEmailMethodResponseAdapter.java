package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponsePort;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;

public class ChangesEmailMethodResponseAdapter implements ChangesEmailMethodResponsePort {
    private final ChangesEmailMethodResponse changesEmailMethodResponse;

    public ChangesEmailMethodResponseAdapter(
            final ChangesEmailMethodResponse changesEmailMethodResponse) {
        this.changesEmailMethodResponse = changesEmailMethodResponse;
    }

    public ChangesEmailMethodResponse adaptee() {
        return changesEmailMethodResponse;
    }
}
