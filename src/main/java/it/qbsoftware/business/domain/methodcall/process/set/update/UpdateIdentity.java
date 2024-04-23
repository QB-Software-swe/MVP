package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;

public interface UpdateIdentity {

    UpdatedResult<IdentityPort> update(SetIdentityMethodCallPort setIdentityMethodCallPort);

}