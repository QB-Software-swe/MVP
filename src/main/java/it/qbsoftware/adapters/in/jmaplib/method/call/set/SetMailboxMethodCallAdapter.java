package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class SetMailboxMethodCallAdapter implements SetMailboxMethodCallPort {
    SetMailboxMethodCall setMailboxMethodCall;

    public SetMailboxMethodCallAdapter(SetMailboxMethodCall setMailboxMethodCall) {
        this.setMailboxMethodCall = setMailboxMethodCall;
    }

    @Override
    public String accountId() {
        return this.accountId();
    }

    @Override
    public String ifInState() {
        return this.ifInState();
    }

    @Override
    public Map<String, MailboxPort> getCreate() {
        return this.getCreate();
    }

    @Override
    public Map<String, Map<String, Object>> getUpdate() {
        return this.getUpdate();
    }
}