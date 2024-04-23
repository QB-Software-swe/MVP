package it.qbsoftware.business.ports.in.jmap.method.call.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;

public interface SetIdentityMethodCallPort {

    public String accountId();

    public String ifInState();

    public Map<String, IdentityPort> getCreate();

    public Map<String, Map<String, Object>> getUpdate();

    public String[] getDestroy();
}
