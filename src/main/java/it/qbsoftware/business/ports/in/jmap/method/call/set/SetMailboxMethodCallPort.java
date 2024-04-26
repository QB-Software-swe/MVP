package it.qbsoftware.business.ports.in.jmap.method.call.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public interface SetMailboxMethodCallPort {

    public String accountId();

    public String ifInState();

    public Map<String, MailboxPort> getCreate();

    public Map<String, Map<String, Object>> getUpdate();

    public String[] getDestroy();
}
