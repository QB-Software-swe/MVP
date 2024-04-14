package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;

public class AbstractIdentifiableEntityAdapter implements AbstractIdentifiableEntityPort{
    AbstractIdentifiableEntity abstractIdentifiableEntity;

    public AbstractIdentifiableEntityAdapter(AbstractIdentifiableEntity abstractIdentifiableEntity){
        this.abstractIdentifiableEntity = abstractIdentifiableEntity;
    }

    @Override
    public String getId() {
        return abstractIdentifiableEntity.getId();
    }

}
