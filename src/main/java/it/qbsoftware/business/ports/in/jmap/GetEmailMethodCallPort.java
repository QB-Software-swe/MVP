package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;

public interface GetEmailMethodCallPort {

    public String accountId();

    public String[] getIds();

    public String[] getProperties();

    public InvocationResultReferencePort getIdsReference();

}
