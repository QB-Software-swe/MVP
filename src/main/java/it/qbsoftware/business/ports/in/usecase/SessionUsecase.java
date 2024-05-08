package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.JmapUrlConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import java.util.Optional;

public interface SessionUsecase {

    public Optional<SessionResourcePort> call(
            String username,
            JmapUrlConfiguration endPointConfiguration,
            CapabilityPort[] serverCapabilities);
}
