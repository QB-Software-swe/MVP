package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponsePort;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse;

public class ChangesThreadMethodResponseAdapter implements ChangesThreadMethodResponsePort {
    private final ChangesThreadMethodResponse changesThreadMethodResponse;

    public ChangesThreadMethodResponseAdapter(final ChangesThreadMethodResponse changesThreadMethodResponse) {
        this.changesThreadMethodResponse = changesThreadMethodResponse;
    }

    public ChangesThreadMethodResponse adaptee() {
        return changesThreadMethodResponse;
    }
}
