package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;

public class StandardUpdateIdentity implements UpdateIdentity {
    @Override
    public UpdatedResult<IdentityPort> update(final SetIdentityMethodCallPort setIdentityMethodCallPort) {
        return new UpdatedResult<>(null, null);
    }
}
