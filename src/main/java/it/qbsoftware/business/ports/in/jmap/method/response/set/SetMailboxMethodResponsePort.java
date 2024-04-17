package it.qbsoftware.business.ports.in.jmap.method.response.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;

public interface SetMailboxMethodResponsePort extends MethodResponsePort {

    Map<String, AbstractIdentifiableEntityPort> getCreated();

}
