package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import rs.ltt.jmap.common.Request.Invocation;

public class InvocationResultReferenceAdapter implements InvocationResultReferencePort{
    Invocation.ResultReference invocationResultReference;

    public InvocationResultReferenceAdapter(Invocation.ResultReference invocationResultReference){
        this.invocationResultReference = invocationResultReference;
    }
}
