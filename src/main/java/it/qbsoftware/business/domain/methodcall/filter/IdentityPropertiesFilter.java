package it.qbsoftware.business.domain.methodcall.filter;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;

public interface IdentityPropertiesFilter {
    public IdentityPort[] filter(final IdentityPort[] identityPorts, final String[] properties)
            throws InvalidArgumentsException;
}
