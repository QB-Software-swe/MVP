package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponsePort;

public interface ChangesThreadMethodCallUsecase {
    public ChangesThreadMethodResponsePort call(
            ChangesThreadMethodCallPort changesThreadMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses)
            throws CannotCalculateChangesException,
                    AccountNotFoundException,
                    InvalidArgumentsException;
}
