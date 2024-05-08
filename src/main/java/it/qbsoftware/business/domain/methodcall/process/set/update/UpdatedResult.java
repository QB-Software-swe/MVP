package it.qbsoftware.business.domain.methodcall.process.set.update;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import java.util.Map;

public record UpdatedResult<EntityType>(
        Map<String, EntityType> updated, Map<String, SetErrorPort> notUpdated) {}
