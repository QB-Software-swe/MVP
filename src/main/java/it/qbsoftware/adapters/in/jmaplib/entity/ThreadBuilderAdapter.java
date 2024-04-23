package it.qbsoftware.adapters.in.jmaplib.entity;

import java.util.Collection;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import rs.ltt.jmap.common.entity.Thread;
import rs.ltt.jmap.common.entity.Thread.ThreadBuilder;

public class ThreadBuilderAdapter implements ThreadBuilderPort {
    private ThreadBuilder threadBuilder = Thread.builder();

    @Override
    public ThreadBuilderPort id(final String id) {
        threadBuilder.id(id);
        return this;
    }

    @Override
    public ThreadBuilderPort emailIds(final Collection<String> emailIds) {
        threadBuilder.emailIds(emailIds);
        return this;
    }

    @Override
    public ThreadPort build() {
        return new ThreadAdapter(threadBuilder.build());
    }

    @Override
    public ThreadBuilderPort reset() {
        threadBuilder = Thread.builder();
        return this;
    }
}
