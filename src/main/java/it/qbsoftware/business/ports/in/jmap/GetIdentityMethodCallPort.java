package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;

public interface GetIdentityMethodCallPort {
    public String accountId();

    public String[] getIds();

    public InvocationResultReferencePort getIdsReference();

    public String[] getProperties();
}
