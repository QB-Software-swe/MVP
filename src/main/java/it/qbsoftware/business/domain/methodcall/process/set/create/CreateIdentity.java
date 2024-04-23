package it.qbsoftware.business.domain.methodcall.process.set.create;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;

public interface CreateIdentity {

    CreatedResult<IdentityPort> create(SetIdentityMethodCallPort setIdentityMethodCallPort)
            throws AccountNotFoundException;

}