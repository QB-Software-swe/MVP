package it.qbsoftware.business.domain.methodcall.filter;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface EmailPropertiesFilter {
    public EmailPort[] filter(final EmailPort[] emails, final String[] properties,
            final EmailFilterBodyPartSettings emailFilterBodyPartSettings) throws InvalidArgumentsException;
}
