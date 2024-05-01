package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.methodresponse.SetEmailSubmissionMethodResponse;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;

public interface SetEmailSubmissionMethodCallUsecase {
    public SetEmailSubmissionMethodResponse call(final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse) throws AccountNotFoundException, SetNotFoundException;
}
