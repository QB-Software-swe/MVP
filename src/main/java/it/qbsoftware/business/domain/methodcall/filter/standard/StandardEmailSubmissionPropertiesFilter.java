package it.qbsoftware.business.domain.methodcall.filter.standard;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.EmailSubmissionPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;

public class StandardEmailSubmissionPropertiesFilter implements EmailSubmissionPropertiesFilter {
    private final EmailSubmissionBuilderPort emailSubmissionBuilderPort;

    public StandardEmailSubmissionPropertiesFilter(final EmailSubmissionBuilderPort emailSubmissionBuilderPort) {
        this.emailSubmissionBuilderPort = emailSubmissionBuilderPort;
    }

    @Override
    public EmailSubmissionPort[] filter(final EmailSubmissionPort[] emailSubmissionPorts, final String[] properties)
            throws InvalidArgumentsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

}
