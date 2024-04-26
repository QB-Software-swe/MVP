package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;

public class StandardUpdateEmail implements UpdateEmail {
    @Override
    public UpdatedResult<EmailPort> update(final SetEmailMethodCallPort setEmailMethodCall) {
        // TODO

        return new UpdatedResult<EmailPort>(null, null);
    }
}
