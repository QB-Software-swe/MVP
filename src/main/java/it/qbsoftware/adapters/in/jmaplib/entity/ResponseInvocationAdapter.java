package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.Response;

public class ResponseInvocationAdapter implements ResponseInvocationPort {
    private Response.Invocation invocation;

    public ResponseInvocationAdapter(final Response.Invocation invocation) {
        this.invocation = invocation;
    }

    public Response.Invocation adaptee() {
        return invocation;
    }
}
