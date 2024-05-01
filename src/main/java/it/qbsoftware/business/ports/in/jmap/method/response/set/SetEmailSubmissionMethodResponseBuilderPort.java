package it.qbsoftware.business.ports.in.jmap.method.response.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public interface SetEmailSubmissionMethodResponseBuilderPort {
    public SetEmailSubmissionMethodResponseBuilderPort accountId(final String accountId);

    public SetEmailSubmissionMethodResponseBuilderPort oldState(final String oldState);

    public SetEmailSubmissionMethodResponseBuilderPort newState(final String newState);

    public SetEmailSubmissionMethodResponseBuilderPort created(final Map<String, EmailSubmissionPort> created);

    public SetEmailSubmissionMethodResponseBuilderPort updated(final Map<String, EmailSubmissionPort> updated);

    public SetEmailSubmissionMethodResponseBuilderPort destroyed(final String[] idDestroyed);

    public SetEmailSubmissionMethodResponseBuilderPort notCreated(final Map<String, SetErrorPort> notCreated);

    public SetEmailSubmissionMethodResponseBuilderPort notUpdated(final Map<String, SetErrorPort> notUpdated);

    public SetEmailSubmissionMethodResponseBuilderPort notDestroyed(final Map<String, SetErrorPort> notDestroyed);

    public SetEmailSubmissionMethodResponsePort build();

    public SetEmailSubmissionMethodResponseBuilderPort reset();
}
