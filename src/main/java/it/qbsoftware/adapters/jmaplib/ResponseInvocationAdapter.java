package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.Response;

public class ResponseInvocationAdapter implements ResponseInvocationPort {
    Response.Invocation invocation;

    public ResponseInvocationAdapter(Response.Invocation invocation) {
        this.invocation = invocation;
    }

    public Response.Invocation invocation() {
        return invocation;
    }
}
