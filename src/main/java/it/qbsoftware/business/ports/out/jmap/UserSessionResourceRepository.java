package it.qbsoftware.business.ports.out.jmap;

import java.util.Optional;

import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;

public interface UserSessionResourceRepository {

    public Optional<SessionResourcePort> retrieve(final String username);
}
