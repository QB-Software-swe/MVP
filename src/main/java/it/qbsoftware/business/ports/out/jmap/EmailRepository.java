package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface EmailRepository {

    public RetrivedEntity<EmailPort> retriveAll(final String accountId);

    public RetrivedEntity<EmailPort> retrive(final String[] ids);

    public EmailPort retriveOne(final String emailId) throws SetNotFoundException;

    public EmailPort destroy(final String emailId) throws SetNotFoundException;

    public void save(final EmailPort emailPort) throws SetSingletonException;

    public void overwrite(final EmailPort emailPort) throws SetNotFoundException;
}
