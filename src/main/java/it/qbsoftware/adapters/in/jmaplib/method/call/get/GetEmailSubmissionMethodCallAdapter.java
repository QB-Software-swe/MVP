package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import rs.ltt.jmap.common.method.call.submission.GetEmailSubmissionMethodCall;

public class GetEmailSubmissionMethodCallAdapter implements GetEmailSubmissionMethodCallPort {
    private GetEmailSubmissionMethodCall getEmailSubmissionMethodCall;

    public GetEmailSubmissionMethodCallAdapter(final GetEmailSubmissionMethodCall getEmailSubmissionMethodCall) {
        this.getEmailSubmissionMethodCall = getEmailSubmissionMethodCall;
    }

    @Override
    public String accountId() {
        return getEmailSubmissionMethodCall.getAccountId();
    }

    @Override
    public String[] getIds() {
        return getEmailSubmissionMethodCall.getIds();
    }

    @Override
    public InvocationResultReferencePort getIdsReference() {
        return new InvocationResultReferenceAdapter(getEmailSubmissionMethodCall.getIdsReference());
    }

    @Override
    public String[] getProperties() {
        return getEmailSubmissionMethodCall.getProperties();
    }

}
