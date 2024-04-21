package it.qbsoftware.business.ports.in.jmap.method.response.set;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

import java.util.Map;

public interface SetEmailMethodResponseBuilderPort {
    public SetEmailMethodResponseBuilderPort accountId(final String state);

    public SetEmailMethodResponseBuilderPort oldState(final String oldState);

    public SetEmailMethodResponseBuilderPort newState(final String newState);

    public SetEmailMethodResponseBuilderPort created(final Map<String, EmailPort> created);

    public SetEmailMethodResponseBuilderPort updated(final Map<String, EmailPort> updated);

    public SetEmailMethodResponseBuilderPort destroyed(final String[] idDestroyed);

    public SetEmailMethodResponseBuilderPort notCreated(final Map<String, SetErrorPort> notCreated);

    public SetEmailMethodResponseBuilderPort notUpdated(final Map<String, SetErrorPort> notUpdated);

    public SetEmailMethodResponseBuilderPort notDestroyed(final Map<String, SetErrorPort> notDestroyed);

    public SetEmailMethodResponsePort build();

    public SetEmailMethodResponseBuilderPort reset();
}
