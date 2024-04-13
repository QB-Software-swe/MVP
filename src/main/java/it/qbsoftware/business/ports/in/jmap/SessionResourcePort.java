package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;

public interface SessionResourcePort {

    public String username();

    public Map<String, AccountPort> accounts();

    public Map<ClassAccountCapabilityPort, String> primaryAccounts();

    public String state();
}