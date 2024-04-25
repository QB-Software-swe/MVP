package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponsePort;
import rs.ltt.jmap.common.entity.EmailSubmission;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse.GetEmailSubmissionMethodResponseBuilder;
import java.util.Arrays;

public class GetEmailSubmissionMethodResponseBuilderAdapter implements GetEmailSubmissionMethodResponseBuilderPort {
    private GetEmailSubmissionMethodResponseBuilder getEmailSubmissionMethodResponseBuilder = GetEmailSubmissionMethodResponse
            .builder();

    @Override
    public GetEmailSubmissionMethodResponseBuilderPort list(final EmailSubmissionPort[] emailSubmissionPort) {
        if (emailSubmissionPort != null) {
            getEmailSubmissionMethodResponseBuilder.list(
                    Arrays.asList(emailSubmissionPort).stream().map(e -> ((EmailSubmissionAdapter) e).adaptee())
                            .toArray(EmailSubmission[]::new));
        } else {
            getEmailSubmissionMethodResponseBuilder.list(null);
        }
        return this;
    }

    @Override
    public GetEmailSubmissionMethodResponseBuilderPort notFound(final String[] ids) {
        getEmailSubmissionMethodResponseBuilder.notFound(ids);
        return this;
    }

    @Override
    public GetEmailSubmissionMethodResponseBuilderPort state(final String state) {
        getEmailSubmissionMethodResponseBuilder.state(state);
        return this;
    }

    @Override
    public GetEmailSubmissionMethodResponsePort build() {
        return new GetEmailSubmissionMethodResponseAdapter(getEmailSubmissionMethodResponseBuilder.build());
    }

    @Override
    public GetEmailSubmissionMethodResponseBuilderPort reset() {
        getEmailSubmissionMethodResponseBuilder = GetEmailSubmissionMethodResponse.builder();
        return this;
    }

}
