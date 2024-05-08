package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponsePort;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse.ChangesEmailMethodResponseBuilder;

public class ChangesEmailMethodResponseBuilderAdapter
        implements ChangesEmailMethodResponseBuilderPort {
    private ChangesEmailMethodResponseBuilder changesEmailMethodResponseBuilder =
            ChangesEmailMethodResponse.builder();

    @Override
    public ChangesEmailMethodResponseBuilderPort accountId(final String accountId) {
        changesEmailMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort oldState(final String oldState) {
        changesEmailMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort newState(final String newState) {
        changesEmailMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort hasMoreChanges(final Boolean hasMoreChanges) {
        changesEmailMethodResponseBuilder.hasMoreChanges(hasMoreChanges);
        return this;
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort created(final String[] ids) {
        changesEmailMethodResponseBuilder.created(ids);
        return this;
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort updated(final String[] ids) {
        changesEmailMethodResponseBuilder.updated(ids);
        return this;
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort destroyed(final String[] ids) {
        changesEmailMethodResponseBuilder.destroyed(ids);
        return this;
    }

    @Override
    public ChangesEmailMethodResponsePort build() {
        return new ChangesEmailMethodResponseAdapter(changesEmailMethodResponseBuilder.build());
    }

    @Override
    public ChangesEmailMethodResponseBuilderPort reset() {
        changesEmailMethodResponseBuilder = ChangesEmailMethodResponse.builder();
        return this;
    }
}
