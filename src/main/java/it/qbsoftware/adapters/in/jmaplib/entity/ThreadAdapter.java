package it.qbsoftware.adapters.in.jmaplib.entity;

import java.util.Collection;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import rs.ltt.jmap.common.entity.Thread;

public class ThreadAdapter implements ThreadPort {
    private Thread thread;

    public ThreadAdapter(final Thread thread) {
        this.thread = thread;
    }

    @Override
    public String getId() {
        return thread.getId();
    }

    @Override
    public Collection<String> getEmailIds() {
        return thread.getEmailIds();
    }

    @Override
    public ThreadBuilderPort toBuilder() {
        return new ThreadBuilderAdapter(
                Thread.builder()
                        .id(thread.getId())
                        .emailIds(thread.getEmailIds()));
    }

    public Thread adaptee() {
        return this.thread;
    }
}
