package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.filter.Filter;

public class JmapFilterAdapter<EntityType> implements JmapFilterPort<EntityType> {
    private final Filter<Email> filter;

    public JmapFilterAdapter(final Filter<Email> filter) {
        this.filter = filter;
    }

    //FIXME: rimuovere se non utilizzato
    public Filter<Email> adapee() {
        return filter;
    }
}
