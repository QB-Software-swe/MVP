package it.qbsoftware.business.ports.in.jmap.method.response.query;

public interface QueryEmailMethodResponseBuilderPort {
    public QueryEmailMethodResponseBuilderPort accountId(String accountId);

    public QueryEmailMethodResponseBuilderPort queryState(String queryState);

    public QueryEmailMethodResponseBuilderPort canCalculateChanges(Boolean canCalculateChanges);

    public QueryEmailMethodResponseBuilderPort position(Long position);

    public QueryEmailMethodResponseBuilderPort ids(String[] ids);

    public QueryEmailMethodResponseBuilderPort total(Long total);

    public QueryEmailMethodResponseBuilderPort limit(Long limit);

    public QueryEmailMethodResponsePort build();

    public QueryEmailMethodResponseBuilderPort reset();
}
