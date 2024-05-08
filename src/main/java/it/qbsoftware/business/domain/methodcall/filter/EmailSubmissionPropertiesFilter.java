package it.qbsoftware.business.domain.methodcall.filter;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;

public interface EmailSubmissionPropertiesFilter {
    public EmailSubmissionPort[] filter(
            final EmailSubmissionPort[] emailSubmissionPorts, final String[] properties)
            throws InvalidArgumentsException;
}
