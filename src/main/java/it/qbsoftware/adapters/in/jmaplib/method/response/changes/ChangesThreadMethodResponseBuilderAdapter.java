package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponsePort;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse.ChangesThreadMethodResponseBuilder;

public class ChangesThreadMethodResponseBuilderAdapter
        implements ChangesThreadMethodResponseBuilderPort {
    private ChangesThreadMethodResponseBuilder changesThreadMethodResponseBuilder =
            ChangesThreadMethodResponse.builder();

    @Override
    public ChangesThreadMethodResponseBuilderPort accountId(final String accountId) {
        changesThreadMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort oldState(final String oldState) {
        changesThreadMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort newState(final String newState) {
        changesThreadMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort hasMoreChanges(final Boolean hasMoreChanges) {
        changesThreadMethodResponseBuilder.hasMoreChanges(hasMoreChanges);
        return this;
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort created(final String[] ids) {
        changesThreadMethodResponseBuilder.created(ids);
        return this;
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort updated(final String[] ids) {
        changesThreadMethodResponseBuilder.updated(ids);
        return this;
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort destroyed(final String[] ids) {
        changesThreadMethodResponseBuilder.destroyed(ids);
        return this;
    }

    @Override
    public ChangesThreadMethodResponsePort build() {
        return new ChangesThreadMethodResponseAdapter(changesThreadMethodResponseBuilder.build());
    }

    @Override
    public ChangesThreadMethodResponseBuilderPort reset() {
        changesThreadMethodResponseBuilder = ChangesThreadMethodResponse.builder();
        return this;
    }
}
