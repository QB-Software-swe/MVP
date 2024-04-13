package it.qbsoftware.business.ports.in.jmap;

public interface ChangesMailboxMethodResponseBuilderPort {

    public ChangesMailboxMethodResponseBuilderPort oldState(final String oldState);

    public ChangesMailboxMethodResponseBuilderPort newState(final String newState);

    public ChangesMailboxMethodResponseBuilderPort updated(String[] updates);

    public ChangesMailboxMethodResponseBuilderPort created(String[] created);

    public ChangesMailboxMethodResponseBuilderPort destroyed(String[] destroyed);

    public ChangesMailboxMethodResponseBuilderPort updatedProperties(String[] updatedProperties);

    public ChangesMailboxMethodResponsePort build();

    public ChangesMailboxMethodResponseBuilderPort reset();
}
