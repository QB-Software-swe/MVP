package it.qbsoftware.business.ports.in.jmap.method.call.query;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapComparatorPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;

public interface QueryEmailMethodCallPort {
    public String getAccountId();

    public JmapFilterPort<EmailPort> getFilter();

    public JmapComparatorPort[] getSort();

    public Long getPosition();

    public String getAnchor();

    public Long getAnchorOffset();

    public Long getLimit();

    public Boolean getCalculateTotal();

    public Boolean getCollapseThreads();
}
