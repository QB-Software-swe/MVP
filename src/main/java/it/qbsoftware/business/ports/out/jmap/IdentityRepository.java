package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import java.util.Map;

public interface IdentityRepository {
    public IdentityPort[] retriveAll(final String accountId);

    public Map<String, IdentityPort> retrive(final String[] threadIds);
}
