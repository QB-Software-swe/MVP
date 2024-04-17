package it.qbsoftware.business.ports.in.jmap.method.response.get;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;

public interface GetIdentityMethodResponseBuilderPort {

    public GetIdentityMethodResponseBuilderPort list(IdentityPort[] identityPorts);

    public GetIdentityMethodResponseBuilderPort notFound(String[] identityIsdsNotFound);

    public GetIdentityMethodResponseBuilderPort state(final String state);

    public GetIdentityMethodResponsePort build();

    public GetIdentityMethodResponseBuilderPort reset();
}
