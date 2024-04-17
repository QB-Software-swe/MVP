package it.qbsoftware.business.ports.in.jmap.entity;

import java.util.Map;

public interface SessionResourcePort {

    public String username();

    public Map<String, AccountPort> accounts();

    public Map<ClassAccountCapabilityPort, String> primaryAccounts();

    public String state();
}