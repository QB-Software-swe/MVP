package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;

//TODO: FORSE È DA SISTEMARE 

public class ThreadAdapter implements ThreadPort{
    public Thread thread;

    public ThreadAdapter(Thread thread) {
        this.thread = thread;
    }
}
