package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponsePort;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;

public class ChangesMailboxMethodResponseAdapter implements ChangesMailboxMethodResponsePort {
    private final ChangesMailboxMethodResponse changesMailboxMethodResponse;

    public ChangesMailboxMethodResponseAdapter(
            final ChangesMailboxMethodResponse changesMailboxMethodResponse) {
        this.changesMailboxMethodResponse = changesMailboxMethodResponse;
    }

    public ChangesMailboxMethodResponse adaptee() {
        return changesMailboxMethodResponse;
    }
}
