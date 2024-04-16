package it.qbsoftware.adapters.in.jmaplib;

import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodResponsePort;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;

public class GetMailboxMethodResponseAdapter implements GetMailboxMethodResponsePort{
    GetMailboxMethodResponse getMailmobMethodResponse;

    public GetMailboxMethodResponseAdapter(GetMailboxMethodResponse getMailmobMethodResponse) {
        this.getMailmobMethodResponse = getMailmobMethodResponse;
    }
}
