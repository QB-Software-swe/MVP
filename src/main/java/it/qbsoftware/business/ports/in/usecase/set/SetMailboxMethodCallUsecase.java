package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetMailboxMethodCallUsecase {

    public MethodResponsePort[] call(SetMailboxMethodCallPort setMailboxMethodCallPort);
}
