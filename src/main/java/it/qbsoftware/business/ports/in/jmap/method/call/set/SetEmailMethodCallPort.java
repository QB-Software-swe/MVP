package it.qbsoftware.business.ports.in.jmap.method.call.set;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import java.util.Map;

public interface SetEmailMethodCallPort {
    public String accountId();

    public String ifInState();

    public Map<String, Map<String, Object>> getUpdate();

    public Map<String, EmailPort> getCreate();

    public String[] getDestroy();
}
