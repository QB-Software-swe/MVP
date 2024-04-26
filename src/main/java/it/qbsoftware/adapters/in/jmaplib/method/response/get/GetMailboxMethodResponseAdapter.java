package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import com.google.inject.Inject;

import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;

public class GetMailboxMethodResponseAdapter implements GetMailboxMethodResponsePort {
    private GetMailboxMethodResponse getMailmobMethodResponse;

    @Inject
    public GetMailboxMethodResponseAdapter(final GetMailboxMethodResponse getMailmobMethodResponse) {
        this.getMailmobMethodResponse = getMailmobMethodResponse;
    }

    public GetMailboxMethodResponse adaptee() {
        return this.getMailmobMethodResponse;
    }
}
