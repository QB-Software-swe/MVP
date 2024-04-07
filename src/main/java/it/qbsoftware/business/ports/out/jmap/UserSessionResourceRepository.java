package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import java.util.Optional;

public interface UserSessionResourceRepository {

    public Optional<SessionResourcePort> retrieve(String username);

    public void save(SessionResourcePort sessionResourcePort);
}
