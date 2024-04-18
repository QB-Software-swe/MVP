package it.qbsoftware.business.domain.methodcall.filter;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public interface MailboxPropertiesFilter {
    public MailboxPort[] filter(final MailboxPort[] identityPorts, final String[] properties)
            throws InvalidArgumentsException;
}
