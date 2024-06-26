package it.qbsoftware.business.ports.in.jmap.method.response.set;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import java.util.Map;

public interface SetMailboxMethodResponsePort extends MethodResponsePort {
    Map<String, AbstractIdentifiableEntityPort> getCreated();
}
