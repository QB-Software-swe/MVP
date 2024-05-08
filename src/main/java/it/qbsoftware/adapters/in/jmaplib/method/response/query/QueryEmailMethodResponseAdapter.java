package it.qbsoftware.adapters.in.jmaplib.method.response.query;

import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponsePort;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse;

public class QueryEmailMethodResponseAdapter implements QueryEmailMethodResponsePort {
    private final QueryEmailMethodResponse queryEmailMethodResponse;

    public QueryEmailMethodResponseAdapter(
            final QueryEmailMethodResponse queryEmailMethodResponse) {
        this.queryEmailMethodResponse = queryEmailMethodResponse;
    }

    public QueryEmailMethodResponse adaptee() {
        return queryEmailMethodResponse;
    }
}
