package it.qbsoftware.business.ports.in.jmap.method.call.set;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import java.util.Map;

public interface SetMailboxMethodCallPort {

    public String accountId();

    public String ifInState();

    public Map<String, MailboxPort> getCreate();

    public Map<String, Map<String, Object>> getUpdate();

    public String[] getDestroy();
}
