package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.call.submission.SetEmailSubmissionMethodCall;

public class SetEmailSubmissionMethodCallAdapter implements SetEmailSubmissionMethodCallPort {
    private final SetEmailSubmissionMethodCall setEmailSubmissionMethodCall;

    public SetEmailSubmissionMethodCallAdapter(
            final SetEmailSubmissionMethodCall setEmailSubmissionMethodCall) {
        this.setEmailSubmissionMethodCall = setEmailSubmissionMethodCall;
    }

    @Override
    public String accountId() {
        return setEmailSubmissionMethodCall.getAccountId();
    }

    @Override
    public String getIfInState() {
        return setEmailSubmissionMethodCall.getIfInState();
    }

    @Override
    public Map<String, Map<String, Object>> getUpdate() {
        return setEmailSubmissionMethodCall.getUpdate();
    }

    @Override
    public Map<String, EmailSubmissionPort> getCreate() {
        return setEmailSubmissionMethodCall.getCreate() != null
                ? setEmailSubmissionMethodCall.getCreate().entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> new EmailSubmissionAdapter(e.getValue())))
                : null;
    }

    @Override
    public String[] getDestroy() {
        return setEmailSubmissionMethodCall.getDestroy();
    }

    @Override
    public Map<String, Map<String, Object>> getOnSuccessUpdateEmail() {
        return setEmailSubmissionMethodCall.getOnSuccessUpdateEmail();
    }
}
