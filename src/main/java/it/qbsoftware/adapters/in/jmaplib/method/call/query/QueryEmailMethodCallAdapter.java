package it.qbsoftware.adapters.in.jmaplib.method.call.query;

import it.qbsoftware.adapters.in.jmaplib.entity.JmapComparatorAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.JmapFilterAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapComparatorPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;
import it.qbsoftware.business.ports.in.jmap.method.call.query.QueryEmailMethodCallPort;
import java.util.Arrays;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;

public class QueryEmailMethodCallAdapter implements QueryEmailMethodCallPort {
    private final QueryEmailMethodCall queryEmailMethodCall;

    public QueryEmailMethodCallAdapter(final QueryEmailMethodCall queryEmailMethodCall) {
        this.queryEmailMethodCall = queryEmailMethodCall;
    }

    @Override
    public String getAccountId() {
        return queryEmailMethodCall.getAccountId();
    }

    @Override
    public JmapFilterPort<EmailPort> getFilter() {
        return new JmapFilterAdapter<EmailPort>(queryEmailMethodCall.getFilter());
    }

    @Override
    public JmapComparatorPort[] getSort() {
        if (queryEmailMethodCall.getSort() != null) {
            return Arrays.asList(queryEmailMethodCall.getSort()).stream()
                    .map(c -> new JmapComparatorAdapter(c))
                    .toArray(JmapComparatorAdapter[]::new);
        }
        return null;
    }

    @Override
    public Long getPosition() {
        return queryEmailMethodCall.getPosition();
    }

    @Override
    public String getAnchor() {
        return queryEmailMethodCall.getAnchor();
    }

    @Override
    public Long getAnchorOffset() {
        return queryEmailMethodCall.getAnchorOffset();
    }

    @Override
    public Long getLimit() {
        return queryEmailMethodCall.getLimit();
    }

    @Override
    public Boolean getCalculateTotal() {
        return queryEmailMethodCall.getCalculateTotal();
    }

    @Override
    public Boolean getCollapseThreads() {
        return queryEmailMethodCall.getCollapseThreads();
    }
}
