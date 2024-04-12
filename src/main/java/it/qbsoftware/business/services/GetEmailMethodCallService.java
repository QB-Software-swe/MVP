package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.usecase.GetEmailMethodCallUsecase;

public class GetEmailMethodCallService implements GetEmailMethodCallUsecase {

    public GetEmailMethodCallService() {}

    @Override
    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort) {
        final InvocationResultReferencePort clientIdsReference = getEmailMethodCallPort.getIdsReference();

        return new MethodResponsePort[] {};
    }

}
