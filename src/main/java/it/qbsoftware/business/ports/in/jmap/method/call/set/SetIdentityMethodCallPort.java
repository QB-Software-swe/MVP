package it.qbsoftware.business.ports.in.jmap.method.call.set;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import java.util.Map;

public interface SetIdentityMethodCallPort {

    public String accountId();

    public String ifInState();

    public Map<String, IdentityPort> getCreate();

    public Map<String, Map<String, Object>> getUpdate();

    public String[] getDestroy();
}
