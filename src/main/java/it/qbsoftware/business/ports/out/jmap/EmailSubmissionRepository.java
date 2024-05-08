package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;

public interface EmailSubmissionRepository {
    public void save(final EmailSubmissionPort emailPort) throws SetNotFoundException;
}
