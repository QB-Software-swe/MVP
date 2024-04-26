package it.qbsoftware.business.ports.in.jmap.method.response.changes;

public interface ChangesIdentityMethodResponseBuilderPort {
    public ChangesIdentityMethodResponseBuilderPort accountId(String accountId);

    public ChangesIdentityMethodResponseBuilderPort oldState(String oldState);

    public ChangesIdentityMethodResponseBuilderPort newState(String newState);

    public ChangesIdentityMethodResponseBuilderPort hasMoreChanges(Boolean hasMoreChanges);

    public ChangesIdentityMethodResponseBuilderPort created(String[] ids);

    public ChangesIdentityMethodResponseBuilderPort updated(String[] ids);

    public ChangesIdentityMethodResponseBuilderPort destroyed(String[] ids);

    public ChangesIdentityMethodResponsePort build();

    public ChangesIdentityMethodResponseBuilderPort reset();
}
