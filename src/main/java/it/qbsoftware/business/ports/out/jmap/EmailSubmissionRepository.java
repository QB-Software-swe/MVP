package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;

public interface EmailSubmissionRepository {
    public RetrivedEntity<EmailSubmissionPort> retriveAll(final String accountId);

    public RetrivedEntity<EmailSubmissionPort> retrive(final String[] ids);

    public EmailSubmissionPort destroy(final String emailId);

    public void save(final EmailSubmissionPort emailPort);
}
