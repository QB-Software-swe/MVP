package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import rs.ltt.jmap.common.entity.Thread;

public class ThreadAdapter implements ThreadPort {
    private Thread thread;

    public ThreadAdapter(final Thread thread) {
        this.thread = thread;
    }

    public Thread adaptee() {
        return this.thread;
    }
}
