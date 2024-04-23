package it.qbsoftware.business.ports.in.jmap.method.response.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public interface SetIdentityMethodResponseBuilderPort {
    public SetIdentityMethodResponseBuilderPort accountId(final String state);

    public SetIdentityMethodResponseBuilderPort oldState(final String oldState);

    public SetIdentityMethodResponseBuilderPort newState(final String newState);

    public SetIdentityMethodResponseBuilderPort created(final Map<String, IdentityPort> created);

    public SetIdentityMethodResponseBuilderPort updated(final Map<String, IdentityPort> updated);

    public SetIdentityMethodResponseBuilderPort destroyed(final String[] idDestroyed);

    public SetIdentityMethodResponseBuilderPort notCreated(final Map<String, SetErrorPort> notCreated);

    public SetIdentityMethodResponseBuilderPort notUpdated(final Map<String, SetErrorPort> notUpdated);

    public SetIdentityMethodResponseBuilderPort notDestroyed(final Map<String, SetErrorPort> notDestroyed);

    public SetIdentityMethodResponsePort build();

    public SetIdentityMethodResponseBuilderPort reset();
}
