package it.qbsoftware.business.ports.in.jmap.method.call.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface SetEmailMethodCallPort {
    public String accountId();

    public String getIfInState();

    public Map<String, Map<String, Object>> getUpdate();

    public Map<String, EmailPort> getCreate();

    public String[] getDestroy();
}
