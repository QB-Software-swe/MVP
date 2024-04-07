package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.capabilities.AccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;

public interface SessionResourcePort {

    public String username();

    public Map<String, AccountPort> accounts();

    public Map<Class<? extends AccountCapabilityPort>, String> primaryAccounts();

    public String state();
}