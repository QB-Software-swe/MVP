package it.qbsoftware.business.domain.methodcall.process.set.create;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import java.util.Map;

public record CreatedResult<EntityType>(
        Map<String, EntityType> created, Map<String, SetErrorPort> notCreated) {}
