package it.qbsoftware.adapters.in.jmaplib.method.response.query;

import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponsePort;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse.QueryEmailMethodResponseBuilder;

public class QueryEmailMethodResponseBuilderAdapter implements QueryEmailMethodResponseBuilderPort {
    private QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
            QueryEmailMethodResponse.builder();

    @Override
    public QueryEmailMethodResponseBuilderPort accountId(final String accountId) {
        queryEmailMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public QueryEmailMethodResponseBuilderPort queryState(final String queryState) {
        queryEmailMethodResponseBuilder.queryState(queryState);
        return this;
    }

    @Override
    public QueryEmailMethodResponseBuilderPort canCalculateChanges(
            final Boolean canCalculateChanges) {
        queryEmailMethodResponseBuilder.canCalculateChanges(canCalculateChanges);
        return this;
    }

    @Override
    public QueryEmailMethodResponseBuilderPort position(final Long position) {
        queryEmailMethodResponseBuilder.position(position);
        return this;
    }

    @Override
    public QueryEmailMethodResponseBuilderPort ids(final String[] ids) {
        queryEmailMethodResponseBuilder.ids(ids);
        return this;
    }

    @Override
    public QueryEmailMethodResponseBuilderPort total(final Long total) {
        queryEmailMethodResponseBuilder.total(total);
        return this;
    }

    @Override
    public QueryEmailMethodResponseBuilderPort limit(final Long limit) {
        queryEmailMethodResponseBuilder.limit(limit);
        return this;
    }

    @Override
    public QueryEmailMethodResponsePort build() {
        return new QueryEmailMethodResponseAdapter(queryEmailMethodResponseBuilder.build());
    }

    @Override
    public QueryEmailMethodResponseBuilderPort reset() {
        queryEmailMethodResponseBuilder = QueryEmailMethodResponse.builder();
        return this;
    }
}
