package it.qbsoftware.adapters.jmaplib;

import rs.ltt.jmap.common.Response;

public class ResponseInvocationAdapter {
    Response.Invocation invocation;

    public ResponseInvocationAdapter(Response.Invocation invocation) {
        this.invocation = invocation;
    }

    public Response.Invocation invocation() {
        return invocation;
    }
}
