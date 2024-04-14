package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;

public interface GetMethodCallPort {
    public String accountId();

    public String[] getIds();

    public InvocationResultReferencePort getIdsReference();

    public String[] getProperties();
}
