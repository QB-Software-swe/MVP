package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;

public class AbstractIdentifiableEntityAdapter implements AbstractIdentifiableEntityPort{
    private AbstractIdentifiableEntity abstractIdentifiableEntity;

    public AbstractIdentifiableEntityAdapter(final AbstractIdentifiableEntity abstractIdentifiableEntity){
        this.abstractIdentifiableEntity = abstractIdentifiableEntity;
    }

    @Override
    public String getId() {
        return abstractIdentifiableEntity.getId();
    }
}
