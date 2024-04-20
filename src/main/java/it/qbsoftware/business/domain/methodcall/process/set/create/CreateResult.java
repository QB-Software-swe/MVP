package it.qbsoftware.business.domain.methodcall.process.set.create;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public record CreateResult<EntityType>(Map<String, EntityType> created, Map<String, SetErrorPort> notCreated) {

}
