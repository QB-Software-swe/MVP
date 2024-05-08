package it.qbsoftware.business.ports.in.jmap.method.response.set;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import java.util.Map;

public interface SetEmailMethodResponsePort extends MethodResponsePort {
    public Map<String, AbstractIdentifiableEntityPort> getCreated();
}
