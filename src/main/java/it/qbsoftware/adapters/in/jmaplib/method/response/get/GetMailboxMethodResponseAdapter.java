package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;

public class GetMailboxMethodResponseAdapter implements GetMailboxMethodResponsePort{
    GetMailboxMethodResponse getMailmobMethodResponse;

    public GetMailboxMethodResponseAdapter(GetMailboxMethodResponse getMailmobMethodResponse) {
        this.getMailmobMethodResponse = getMailmobMethodResponse;
    }
}