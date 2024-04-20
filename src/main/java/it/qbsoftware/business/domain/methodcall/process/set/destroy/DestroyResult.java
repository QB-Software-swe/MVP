package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;

public record DestroyResult(String[] destroyed, Map<String, SetErrorPort> notDestroyed) {
    
}
