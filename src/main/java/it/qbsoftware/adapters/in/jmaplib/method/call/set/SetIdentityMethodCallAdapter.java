package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.call.identity.SetIdentityMethodCall;

public class SetIdentityMethodCallAdapter implements SetIdentityMethodCallPort {
    private SetIdentityMethodCall setIdentityMethodCall;

    public SetIdentityMethodCallAdapter(final SetIdentityMethodCall setIdentityMethodCall) {
        this.setIdentityMethodCall = setIdentityMethodCall;
    }

    @Override
    public String accountId() {
        return setIdentityMethodCall.getAccountId();
    }

    @Override
    public String ifInState() {
        return setIdentityMethodCall.getIfInState();
    }

    @Override
    public Map<String, IdentityPort> getCreate() {
        return setIdentityMethodCall.getCreate().entrySet().stream()
                .collect(Collectors.toMap(i -> i.getKey(), i -> new IdentityAdapter(i.getValue())));
    }

    @Override
    public Map<String, Map<String, Object>> getUpdate() {
        return setIdentityMethodCall.getUpdate();
    }

    @Override
    public String[] getDestroy() {
        return setIdentityMethodCall.getDestroy();
    }
}
