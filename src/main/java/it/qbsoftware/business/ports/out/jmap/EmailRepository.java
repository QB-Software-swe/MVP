package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface EmailRepository {

    public EmailPort[] retrive(final String accountId);

    public boolean destroy(final String emailId);

    public boolean save(final EmailPort emailPort);
}
