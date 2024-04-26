package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse;

public class GetThreadMethodResponseAdapter implements GetThreadMethodResponsePort {
    private GetThreadMethodResponse getThreadMethodResponse;

    public GetThreadMethodResponseAdapter(final GetThreadMethodResponse getThreadMethodResponse) {
        this.getThreadMethodResponse = getThreadMethodResponse;
    }

    public GetThreadMethodResponse adaptee() {
        return getThreadMethodResponse;
    }
}
