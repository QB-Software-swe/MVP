package it.qbsoftware.business.ports.in.jmap.method.call.get;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;

public interface GetMethodCallPort {
    public String accountId();

    public String[] getIds();

    public InvocationResultReferencePort getIdsReference();

    public String[] getProperties();
}
