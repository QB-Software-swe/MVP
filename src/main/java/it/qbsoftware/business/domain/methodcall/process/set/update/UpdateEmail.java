package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;

public interface UpdateEmail {

    UpdatedResult<EmailPort> update(SetEmailMethodCallPort setEmailMethodCallPort);

}