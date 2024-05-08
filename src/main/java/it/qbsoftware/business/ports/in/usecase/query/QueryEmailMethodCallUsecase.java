package it.qbsoftware.business.ports.in.usecase.query;

import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.query.QueryAnchorNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.query.QueryEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponsePort;

public interface QueryEmailMethodCallUsecase {
    public QueryEmailMethodResponsePort call(
            QueryEmailMethodCallPort queryEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws QueryAnchorNotFoundException, AccountNotFoundException;
}
