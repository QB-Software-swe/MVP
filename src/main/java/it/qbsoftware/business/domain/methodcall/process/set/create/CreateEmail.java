package it.qbsoftware.business.domain.methodcall.process.set.create;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;

public interface CreateEmail {

    CreatedResult<EmailPort> create(final SetEmailMethodCallPort setEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws AccountNotFoundException;

}