package it.qbsoftware.business.ports.out.jmap;

import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;

public interface ThreadRepository {
    public RetrivedEntity<ThreadPort> retriveAll(final String accountId);

    public RetrivedEntity<ThreadPort> retrive(final String[] ids);

    public ThreadPort retriveOne(final String id);

    public void save(final ThreadPort threadPort);
}
