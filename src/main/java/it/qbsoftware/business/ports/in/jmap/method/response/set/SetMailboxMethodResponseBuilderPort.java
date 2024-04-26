package it.qbsoftware.business.ports.in.jmap.method.response.set;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

import java.util.Map;

public interface SetMailboxMethodResponseBuilderPort {
    public SetMailboxMethodResponseBuilderPort accountId(final String state);

    public SetMailboxMethodResponseBuilderPort oldState(final String oldState);

    public SetMailboxMethodResponseBuilderPort newState(final String newState);

    public SetMailboxMethodResponseBuilderPort created(final Map<String, MailboxPort> created);

    public SetMailboxMethodResponseBuilderPort updated(final Map<String, MailboxPort> updated);

    public SetMailboxMethodResponseBuilderPort destroyed(final String[] idDestroyed);

    public SetMailboxMethodResponseBuilderPort notCreated(final Map<String, SetErrorPort> notCreated);

    public SetMailboxMethodResponseBuilderPort notUpdated(final Map<String, SetErrorPort> notUpdated);

    public SetMailboxMethodResponseBuilderPort notDestroyed(final Map<String, SetErrorPort> notDestroyed);

    public SetMailboxMethodResponsePort build();

    public SetMailboxMethodResponseBuilderPort reset();
}
