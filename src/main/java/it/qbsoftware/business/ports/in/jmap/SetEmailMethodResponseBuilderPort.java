package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface SetEmailMethodResponseBuilderPort {
    public SetEmailMethodResponseBuilderPort created(final String serverEmailId, final EmailPort emailPort);

    public SetEmailMethodResponseBuilderPort destroyed(final String ids[]);

    public SetEmailMethodResponseBuilderPort notDestroyed(final String ids[]);

    public SetEmailMethodResponsePort build();

    public SetEmailMethodResponseBuilderPort reset();
}
