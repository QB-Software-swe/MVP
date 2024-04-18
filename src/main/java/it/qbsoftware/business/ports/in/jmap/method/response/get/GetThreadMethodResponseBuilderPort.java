package it.qbsoftware.business.ports.in.jmap.method.response.get;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;

public interface GetThreadMethodResponseBuilderPort {

    public GetThreadMethodResponseBuilderPort list(final ThreadPort[] threads);

    public GetThreadMethodResponseBuilderPort notFound(final String[] ids);

    public GetThreadMethodResponseBuilderPort state(final String state);

    public GetThreadMethodResponsePort build();

    public GetThreadMethodResponseBuilderPort reset();
}
