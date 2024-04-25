package it.qbsoftware.business.ports.in.usecase.changes;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponsePort;

public interface ChangesEmailSubmissionMethodCallUsecase {
    public ChangesEmailSubmissionMethodResponsePort call(ChangesEmailSubmissionMethodCallPort changesSubmissionMethodCallPort,
            ListMultimapPort<String, ResponseInvocationPort> previousresponses) throws AccountNotFoundException, InvalidArgumentsException, CannotCalculateChangesException;
}
