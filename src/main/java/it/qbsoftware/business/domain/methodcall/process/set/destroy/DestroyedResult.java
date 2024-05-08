package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import java.util.Map;

public record DestroyedResult(String[] destroyed, Map<String, SetErrorPort> notDestroyed) {}
