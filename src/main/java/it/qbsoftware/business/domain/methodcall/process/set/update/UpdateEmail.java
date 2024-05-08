package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import java.util.Map;

public interface UpdateEmail {

    UpdatedResult<EmailPort> update(
            SetEmailMethodCallPort setEmailMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException;

    UpdatedResult<EmailPort> update(
            SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses,
            Map<String, String> successSubmissionEmailIdResolve)
            throws AccountNotFoundException;
}
