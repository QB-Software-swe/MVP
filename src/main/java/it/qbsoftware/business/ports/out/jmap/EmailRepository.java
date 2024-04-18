package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface EmailRepository {

    public RetrivedEntity<EmailPort> retriveAll(final String accountId);

    public RetrivedEntity<EmailPort> retrive(final String[] ids);

    public boolean destroy(final String emailId);

    public boolean save(final EmailPort emailPort);
}
