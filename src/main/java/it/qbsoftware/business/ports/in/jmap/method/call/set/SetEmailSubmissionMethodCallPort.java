package it.qbsoftware.business.ports.in.jmap.method.call.set;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import java.util.Map;

public interface SetEmailSubmissionMethodCallPort {
    public String accountId();

    public String getIfInState();

    public Map<String, Map<String, Object>> getUpdate();

    public Map<String, Map<String, Object>> getOnSuccessUpdateEmail();

    public Map<String, EmailSubmissionPort> getCreate();

    public String[] getDestroy();
}
