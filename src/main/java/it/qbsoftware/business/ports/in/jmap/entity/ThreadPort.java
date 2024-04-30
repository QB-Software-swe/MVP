package it.qbsoftware.business.ports.in.jmap.entity;

import java.util.Collection;

public interface ThreadPort {
    public String getId();

    public Collection<String> getEmailIds();

    public ThreadBuilderPort toBuilder();
}
