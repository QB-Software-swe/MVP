package it.qbsoftware.business.ports.in.jmap.method.response.changes;

public interface ChangesMailboxMethodResponseBuilderPort {
    public ChangesMailboxMethodResponseBuilderPort accountId(String accountId);

    public ChangesMailboxMethodResponseBuilderPort oldState(String oldState);

    public ChangesMailboxMethodResponseBuilderPort newState(String newState);

    public ChangesMailboxMethodResponseBuilderPort hasMoreChanges(Boolean hasMoreChanges);

    public ChangesMailboxMethodResponseBuilderPort created(String[] ids);

    public ChangesMailboxMethodResponseBuilderPort updated(String[] ids);

    public ChangesMailboxMethodResponseBuilderPort destroyed(String[] ids);

    public ChangesMailboxMethodResponseBuilderPort updatedProperties(String[] updateProperties);

    public ChangesMailboxMethodResponsePort build();

    public ChangesEmailMethodResponseBuilderPort reset();
}
