package it.qbsoftware.business.domain.methodcall.process.set.create;

import java.util.HashMap;
import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;

public class StandardCreateEmailSubmission {
    public CreatedResult<EmailSubmissionPort> create(
            final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort) {
        final Map<String, EmailSubmissionPort> created = new HashMap<>();
        final Map<String, SetErrorPort> notCreated = new HashMap<>();

        final var x = setEmailSubmissionMethodCallPort
                .getCreate();

        if (x != null) {
            for (final Map.Entry<String, EmailSubmissionPort> emailSubmissionToCreate : x.entrySet()) {
                // TODO
            }
        }

        return new CreatedResult<>(null, null);
    }

    private EmailSubmissionPort createSubmission(final EmailSubmissionPort emailSubmissionPort) {
        return null; // TODO
    }
}
