package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;

public interface IdentityRepository {

    public RetrivedEntity<IdentityPort> retriveAll(final String accountId);

    public RetrivedEntity<IdentityPort> retrive(final String[] ids);

    public void save(final IdentityPort identityPort) throws SetSingletonException;

    public void destroy(final String idToDestroy) throws SetNotFoundException;
}
