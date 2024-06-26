package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponsePort;

public interface ChangesIdentityMethodCallUsecase {
    public ChangesIdentityMethodResponsePort call(
            ChangesIdentityMethodCallPort changesMailboxMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses)
            throws AccountNotFoundException,
                    InvalidArgumentsException,
                    CannotCalculateChangesException;
}
