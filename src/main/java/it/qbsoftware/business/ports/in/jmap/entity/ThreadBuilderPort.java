package it.qbsoftware.business.ports.in.jmap.entity;

import java.util.Collection;

public interface ThreadBuilderPort {

    public ThreadBuilderPort id(final String id);

    public ThreadBuilderPort emailIds(final Collection<String> emailIds);

    public ThreadPort build();

    public ThreadBuilderPort reset();
}
