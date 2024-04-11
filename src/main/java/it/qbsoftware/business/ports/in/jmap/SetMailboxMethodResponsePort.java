package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;

public interface SetMailboxMethodResponsePort extends MethodResponsePort {

    Map<String, AbstractIdentifiableEntityPort> getCreated();

}
