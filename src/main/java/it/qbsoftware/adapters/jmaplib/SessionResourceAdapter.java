package it.qbsoftware.adapters.jmaplib;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.capabilities.AccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import rs.ltt.jmap.common.SessionResource;

public class SessionResourceAdapter implements SessionResourcePort{
    SessionResource sessionResource;

    public SessionResourceAdapter(SessionResource sessionResource){
        this.sessionResource = sessionResource;
    }

    @Override
    public String username() {
        return sessionResource.getUsername();
    }

    @Override
    public Map<String, AccountPort> accounts() {
        return sessionResource.getAccounts();

    }

    @Override
    public Map<Class<? extends AccountCapabilityPort>, String> primaryAccounts() {
        return sessionResource.getPrimaryAccount();
    }

    @Override
    public String state() {
        return sessionResource.getState();
    }

}
