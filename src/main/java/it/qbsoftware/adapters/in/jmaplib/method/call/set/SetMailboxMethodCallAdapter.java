package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import java.util.Map;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;
import java.util.stream.Collectors;

public class SetMailboxMethodCallAdapter implements SetMailboxMethodCallPort {
    SetMailboxMethodCall setMailboxMethodCall;

    public SetMailboxMethodCallAdapter(SetMailboxMethodCall setMailboxMethodCall) {
        this.setMailboxMethodCall = setMailboxMethodCall;
    }

    @Override
    public String accountId() {
        return setMailboxMethodCall.getAccountId();
    }

    @Override
    public String ifInState() {
        return setMailboxMethodCall.getIfInState();
    }

    @Override
    public Map<String, MailboxPort> getCreate() {
        return this.setMailboxMethodCall.getCreate().entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> new MailboxAdapter(entry.getValue())));
    }

    @Override
    public Map<String, Map<String, Object>> getUpdate() {
        return setMailboxMethodCall.getUpdate();
    }
}