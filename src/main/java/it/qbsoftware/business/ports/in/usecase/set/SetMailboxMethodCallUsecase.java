package it.qbsoftware.business.ports.in.usecase.set;

import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.SetMailboxMethodCallPort;

public interface SetMailboxMethodCallUsecase {

    public MethodResponsePort[] call(SetMailboxMethodCallPort setMailboxMethodCallPort);
}
