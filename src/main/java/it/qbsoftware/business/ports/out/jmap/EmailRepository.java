package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.repository.EntityRetriveRepository;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface EmailRepository extends EntityRetriveRepository<EmailPort> {

    public EmailPort[] retriveAll(final String accountId);

    public boolean destroy(final String emailId);

    public boolean save(final EmailPort emailPort);
}
