package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import java.util.Map;
import java.util.stream.Collectors;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

public class SetEmailMethodCallAdapter implements SetEmailMethodCallPort {
    private SetEmailMethodCall setEmailMethodCall;

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
        return setEmailMethodCall.getCreate().entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> new EmailAdapter(e.getValue())));
    }

    @Override
    public String[] getDestroy() {
        return setEmailMethodCall.getDestroy();
    }
}
