package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public record DestroyedResult(String[] destroyed, Map<String, SetErrorPort> notDestroyed) {
    
}
