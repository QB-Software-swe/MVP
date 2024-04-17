package it.qbsoftware.business.ports.in.jmap.method.response.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetEmailMethodResponsePort extends MethodResponsePort {
    public Map<String, AbstractIdentifiableEntityPort> getCreated();
}
