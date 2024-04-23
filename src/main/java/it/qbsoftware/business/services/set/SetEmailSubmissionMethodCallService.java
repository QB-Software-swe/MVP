package it.qbsoftware.business.services.set;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailSubmissionMethodCallUsecase;

public class SetEmailSubmissionMethodCallService implements SetEmailSubmissionMethodCallUsecase {

    @Override
    public MethodResponsePort[] call(final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }

}
