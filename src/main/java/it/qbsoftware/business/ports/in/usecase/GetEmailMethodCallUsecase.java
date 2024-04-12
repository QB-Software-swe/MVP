package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;

public interface GetEmailMethodCallUsecase {

    public MethodResponsePort[] call(final GetEmailMethodCallPort getEmailMethodCallPort);
}
