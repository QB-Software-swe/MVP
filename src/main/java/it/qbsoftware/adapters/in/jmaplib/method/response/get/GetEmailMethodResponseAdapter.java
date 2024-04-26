package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponsePort;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;

public class GetEmailMethodResponseAdapter implements GetEmailMethodResponsePort {
    private final GetEmailMethodResponse getEmailMethodResponse;

    public GetEmailMethodResponseAdapter(final GetEmailMethodResponse getEmailMethodResponse) {
        this.getEmailMethodResponse = getEmailMethodResponse;
    }

    public GetEmailMethodResponse adaptee() {
        return this.getEmailMethodResponse;
    }
}
