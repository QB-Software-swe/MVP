package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import java.util.Optional;

public interface UserSessionResourceRepository {

    public Optional<SessionResourcePort> retrieve(final String username);
}
