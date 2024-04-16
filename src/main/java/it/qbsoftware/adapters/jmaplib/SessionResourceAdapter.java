package it.qbsoftware.adapters.jmaplib;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;

public class SessionResourceAdapter implements SessionResourcePort {
    SessionResource sessionResource;

    public SessionResourceAdapter(SessionResource sessionResource) {
        this.sessionResource = sessionResource;
    }

    @Override
    public String username() {
        return sessionResource.getUsername();
    }

    @Override
    public Map<String, AccountPort> accounts() {
        return sessionResource.getAccounts().entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, e -> new AccountAdapter(e.getValue())));
    }

    @Override
    public Map<ClassAccountCapabilityPort, String> primaryAccounts() {
        Map<ClassAccountCapabilityPort, String> returnMap = new HashMap<ClassAccountCapabilityPort, String>();

        returnMap.put(new ClassAccountCapabilityAdapter(MailAccountCapability.class),
                sessionResource.getPrimaryAccount(MailAccountCapability.class));

        return returnMap;
    }

    @Override
    public String state() {
        return sessionResource.getState();
    }

    public SessionResource adaptee() {
        return this.sessionResource;
    }
}
