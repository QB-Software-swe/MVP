package it.qbsoftware.business.ports.in.jmap.method.response.changes;

public interface ChangesThreadMethodResponseBuilderPort {
    public ChangesThreadMethodResponseBuilderPort accountId(String accountId);

    public ChangesThreadMethodResponseBuilderPort oldState(String oldState);

    public ChangesThreadMethodResponseBuilderPort newState(String newState);

    public ChangesThreadMethodResponseBuilderPort hasMoreChanges(Boolean hasMoreChanges);

    public ChangesThreadMethodResponseBuilderPort created(String[] ids);

    public ChangesThreadMethodResponseBuilderPort updated(String[] ids);

    public ChangesThreadMethodResponseBuilderPort destroyed(String[] ids);

    public ChangesThreadMethodResponsePort build();

    public ChangesThreadMethodResponseBuilderPort reset();
}
