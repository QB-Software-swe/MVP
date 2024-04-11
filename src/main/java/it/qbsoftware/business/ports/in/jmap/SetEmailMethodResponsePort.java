package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;

import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity; //FIXME: (grave) leak della libreria nella business logic

public interface SetEmailMethodResponsePort extends MethodResponsePort {
    public Map<String, AbstractIdentifiableEntity> getCreated();
}
