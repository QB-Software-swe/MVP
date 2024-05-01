package it.qbsoftware.business.domain.methodresponse;

import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;

public record SetEmailSubmissionMethodResponse(
        SetEmailSubmissionMethodResponsePort setEmailSubmissionMethodResponsePort,
        SetEmailMethodResponsePort setEmailMethodResponsePort) {

}
