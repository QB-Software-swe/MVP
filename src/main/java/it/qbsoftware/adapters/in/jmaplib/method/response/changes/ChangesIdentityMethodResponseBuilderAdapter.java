package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponsePort;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse.ChangesIdentityMethodResponseBuilder;

public class ChangesIdentityMethodResponseBuilderAdapter implements ChangesIdentityMethodResponseBuilderPort {
    private ChangesIdentityMethodResponseBuilder changesIdentityMethodResponseBuilder = ChangesIdentityMethodResponse
            .builder();

    @Override
    public ChangesIdentityMethodResponseBuilderPort accountId(final String accountId) {
        changesIdentityMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort oldState(final String oldState) {
        changesIdentityMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort newState(final String newState) {
        changesIdentityMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort hasMoreChanges(final Boolean hasMoreChanges) {
        changesIdentityMethodResponseBuilder.hasMoreChanges(hasMoreChanges);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort created(final String[] ids) {
        changesIdentityMethodResponseBuilder.created(ids);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort updated(final String[] ids) {
        changesIdentityMethodResponseBuilder.updated(ids);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort destroyed(final String[] ids) {
        changesIdentityMethodResponseBuilder.destroyed(ids);
        return this;
    }

    @Override
    public ChangesIdentityMethodResponsePort build() {
        return new ChangesIdentityMethodResponseAdapter(changesIdentityMethodResponseBuilder.build());
    }

    @Override
    public ChangesIdentityMethodResponseBuilderPort reset() {
        changesIdentityMethodResponseBuilder = ChangesIdentityMethodResponse.builder();
        return this;
    }
}
