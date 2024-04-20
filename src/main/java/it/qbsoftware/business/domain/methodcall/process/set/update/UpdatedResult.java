package it.qbsoftware.business.domain.methodcall.process.set.update;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public record UpdatedResult<EntityType>(Map<String, EntityType> updated, Map<String, SetErrorPort> notUpdated) {

}
