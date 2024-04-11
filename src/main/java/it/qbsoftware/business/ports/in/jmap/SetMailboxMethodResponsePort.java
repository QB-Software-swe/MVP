package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;

import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity; //FIXME: leak

public interface SetMailboxMethodResponsePort extends MethodResponsePort {

    Map<String, AbstractIdentifiableEntity> getCreated();

}
