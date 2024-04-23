package it.qbsoftware.business.ports.in.jmap.method.response.changes;

public interface ChangesEmailSubmissionMethodResponseBuilderPort {
    public ChangesEmailSubmissionMethodResponseBuilderPort accountId(String accountId);

    public ChangesEmailSubmissionMethodResponseBuilderPort oldState(String oldState);

    public ChangesEmailSubmissionMethodResponseBuilderPort newState(String newState);

    public ChangesEmailSubmissionMethodResponseBuilderPort hasMoreChanges(Boolean hasMoreChanges);

    public ChangesEmailSubmissionMethodResponseBuilderPort created(String[] ids);

    public ChangesEmailSubmissionMethodResponseBuilderPort updated(String[] ids);

    public ChangesEmailSubmissionMethodResponseBuilderPort destroyed(String[] ids);

    public ChangesEmailSubmissionMethodResponsePort build();

    public ChangesEmailSubmissionMethodResponseBuilderPort reset();
}
