package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;

public interface SetEmailMethodResponsePort extends MethodResponsePort {
    public Map<String, AbstractIdentifiableEntityPort> getCreated();
}
