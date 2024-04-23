package it.qbsoftware.business.ports.in.jmap.method.response.changes;

public interface ChangesEmailMethodResponseBuilderPort {
    public ChangesEmailMethodResponseBuilderPort accountId(String accountId);

    public ChangesEmailMethodResponseBuilderPort oldState(String oldState);

    public ChangesEmailMethodResponseBuilderPort newState(String newState);

    public ChangesEmailMethodResponseBuilderPort hasMoreChanges(Boolean hasMoreChanges);

    public ChangesEmailMethodResponseBuilderPort created(String[] ids);

    public ChangesEmailMethodResponseBuilderPort updated(String[] ids);

    public ChangesEmailMethodResponseBuilderPort destroyed(String[] ids);

    public ChangesEmailMethodResponsePort build();

    public ChangesEmailMethodResponseBuilderPort reset();
}
