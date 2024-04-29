package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.JmapComparatorPort;
import rs.ltt.jmap.common.entity.Comparator;

public class JmapComparatorAdapter implements JmapComparatorPort {
    private final Comparator comparator;

    public JmapComparatorAdapter(final Comparator comparator) {
        this.comparator = comparator;
    }
}
