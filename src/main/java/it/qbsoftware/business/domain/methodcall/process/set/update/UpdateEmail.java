package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;

public interface UpdateEmail {

    UpdatedResult<EmailPort> update(SetEmailMethodCallPort setEmailMethodCallPort, ListMultimapPort<String, ResponseInvocationPort> previousResponses) throws AccountNotFoundException;

}