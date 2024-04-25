package it.qbsoftware.business.domain.methodcall.filter.standard;

import java.util.ArrayList;
import java.util.List;

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
        if (properties == null) {
            return emailSubmissionPorts;
        }

        final List<EmailSubmissionPort> filtredEmailSubmission = new ArrayList<>();
        for (final EmailSubmissionPort emailSubmissionPort : emailSubmissionPorts) {
            filtredEmailSubmission.add(emailSubmissionFilter(emailSubmissionPort, properties));
        }

        return filtredEmailSubmission.toArray(EmailSubmissionPort[]::new);
    }

    private EmailSubmissionPort emailSubmissionFilter(final EmailSubmissionPort emailSubmissionPort,
            final String[] properties) {
        return emailSubmissionPort; // TODO implementare il filtro
    }

}
