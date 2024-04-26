package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;

public interface DestroyIdentity {

    DestroyedResult destroy(SetIdentityMethodCallPort setIdentityMethodCallPort)
            throws AccountNotFoundException;

}