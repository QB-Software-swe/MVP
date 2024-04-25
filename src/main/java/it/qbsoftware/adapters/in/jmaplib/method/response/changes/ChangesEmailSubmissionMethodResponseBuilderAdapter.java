package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponsePort;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse.ChangesEmailSubmissionMethodResponseBuilder;

public class ChangesEmailSubmissionMethodResponseBuilderAdapter
        implements ChangesEmailSubmissionMethodResponseBuilderPort {
    private ChangesEmailSubmissionMethodResponseBuilder changesEmailSubmissionMethodResponseBuilder = ChangesEmailSubmissionMethodResponse
            .builder();

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort accountId(final String accountId) {
        changesEmailSubmissionMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort oldState(final String oldState) {
        changesEmailSubmissionMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort newState(final String newState) {
        changesEmailSubmissionMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort hasMoreChanges(final Boolean hasMoreChanges) {
        changesEmailSubmissionMethodResponseBuilder.hasMoreChanges(hasMoreChanges);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort created(final String[] ids) {
        changesEmailSubmissionMethodResponseBuilder.created(ids);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort updated(final String[] ids) {
        changesEmailSubmissionMethodResponseBuilder.updated(ids);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort destroyed(final String[] ids) {
        changesEmailSubmissionMethodResponseBuilder.destroyed(ids);
        return this;
    }

    @Override
    public ChangesEmailSubmissionMethodResponsePort build() {
        return new ChangesEmailSubmissionMethodResponseAdapter(changesEmailSubmissionMethodResponseBuilder.build());
    }

    @Override
    public ChangesEmailSubmissionMethodResponseBuilderPort reset() {
        changesEmailSubmissionMethodResponseBuilder = ChangesEmailSubmissionMethodResponse.builder();
        return this;
    }

}
