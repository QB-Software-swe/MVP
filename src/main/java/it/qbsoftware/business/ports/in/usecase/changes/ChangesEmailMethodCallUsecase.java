package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponsePort;

public interface ChangesEmailMethodCallUsecase {
    public ChangesEmailMethodResponsePort call(ChangesEmailMethodCallPort changesEmailMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses) throws AccountNotFoundException, InvalidArgumentsException, CannotCalculateChangesException;
}
