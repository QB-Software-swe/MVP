package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponsePort;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse.ChangesMailboxMethodResponseBuilder;

public class ChangesMailboxMethodResponseBuilderAdapter implements ChangesMailboxMethodResponseBuilderPort {
    private ChangesMailboxMethodResponseBuilder changesMailboxMethodResponseBuilder = ChangesMailboxMethodResponse
            .builder();

    @Override
    public ChangesMailboxMethodResponseBuilderPort accountId(final String accountId) {
        changesMailboxMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort oldState(final String oldState) {
        changesMailboxMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort newState(final String newState) {
        changesMailboxMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort hasMoreChanges(final Boolean hasMoreChanges) {
        changesMailboxMethodResponseBuilder.hasMoreChanges(hasMoreChanges);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort created(final String[] ids) {
        changesMailboxMethodResponseBuilder.created(ids);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort updated(final String[] ids) {
        changesMailboxMethodResponseBuilder.updated(ids);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort destroyed(final String[] ids) {
        changesMailboxMethodResponseBuilder.destroyed(ids);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort updatedProperties(final String[] updateProperties) {
        changesMailboxMethodResponseBuilder.updated(updateProperties);
        return this;
    }

    @Override
    public ChangesMailboxMethodResponsePort build() {
        return new ChangesMailboxMethodResponseAdapter(changesMailboxMethodResponseBuilder.build());
    }

    @Override
    public ChangesMailboxMethodResponseBuilderPort reset() {
        changesMailboxMethodResponseBuilder = ChangesMailboxMethodResponse.builder();
        return this;
    }

}
