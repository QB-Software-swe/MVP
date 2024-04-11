package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.ResultReferencePort;

public interface GetMailboxMethodCallPort {
    public String accountId();

    public String[] getIds();

    public ResultReferencePort getIdsReference();
}
