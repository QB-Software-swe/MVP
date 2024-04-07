package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;

import java.util.HashMap;
import java.util.Optional;

public interface SessionUsecase {

    public Optional<SessionResourcePort> call(String username, EndPointConfiguration endPointConfiguration,
            HashMap<Class<? extends CapabilityPort>, CapabilityPort> serverCapabilities);
}
