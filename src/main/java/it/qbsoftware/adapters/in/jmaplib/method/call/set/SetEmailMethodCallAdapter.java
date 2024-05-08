package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

public class SetEmailMethodCallAdapter implements SetEmailMethodCallPort {
    private final SetEmailMethodCall setEmailMethodCall;

    public SetEmailMethodCallAdapter(final SetEmailMethodCall setEmailMethodCall) {
        this.setEmailMethodCall = setEmailMethodCall;
    }

    @Override
    public String accountId() {
        return setEmailMethodCall.getAccountId();
    }

    @Override
    public String ifInState() {
        return setEmailMethodCall.getIfInState();
    }

    @Override
    public Map<String, Map<String, Object>> getUpdate() {
        return setEmailMethodCall.getUpdate();
    }

    @Override
    public Map<String, EmailPort> getCreate() {
        Map<String, Email> emailMap = setEmailMethodCall.getCreate();
        return emailMap != null
                ? emailMap.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(), e -> new EmailAdapter(e.getValue())))
                : null;
    }

    @Override
    public String[] getDestroy() {
        return setEmailMethodCall.getDestroy();
    }
}
