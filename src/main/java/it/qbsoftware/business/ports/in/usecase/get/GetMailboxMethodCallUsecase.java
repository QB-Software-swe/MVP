package it.qbsoftware.business.ports.in.usecase.get;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;

public interface GetMailboxMethodCallUsecase {
    public GetMailboxMethodResponsePort call(final GetMailboxMethodCallPort getMailboxMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) throws InvalidArgumentsException, AccountNotFoundException, InvalidResultReferenceExecption;
}
