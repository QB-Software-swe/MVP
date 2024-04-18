package it.qbsoftware.business.domain.methodcall.filter;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;

public interface ThreadPropertiesFilter {
    public ThreadPort[] filter(final ThreadPort[] threadPorts, final String[] properties)
            throws InvalidArgumentsException;
}
