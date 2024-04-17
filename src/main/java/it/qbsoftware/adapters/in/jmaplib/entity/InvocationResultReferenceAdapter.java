package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import rs.ltt.jmap.common.Request.Invocation;

public class InvocationResultReferenceAdapter implements InvocationResultReferencePort {
    Invocation.ResultReference invocationResultReference;

    public InvocationResultReferenceAdapter(final Invocation.ResultReference invocationResultReference) {
        this.invocationResultReference = invocationResultReference;
    }

    public Invocation.ResultReference invocationResultReference() {
        return this.invocationResultReference;
    }
}
