package it.qbsoftware.business.ports.in.jmap.method.response.get;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;

public interface GetEmailSubmissionMethodResponseBuilderPort {
    public GetEmailSubmissionMethodResponseBuilderPort list(
            final EmailSubmissionPort[] emailSubmissionPort);

    public GetEmailSubmissionMethodResponseBuilderPort notFound(final String[] ids);

    public GetEmailSubmissionMethodResponseBuilderPort state(final String state);

    public GetEmailSubmissionMethodResponsePort build();

    public GetEmailSubmissionMethodResponseBuilderPort reset();
}
