package it.qbsoftware.business.ports.in.jmap.entity;

import java.util.Map;

import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;

public interface SessionResourceBuilderPort {
    public SessionResourceBuilderPort username(String name);

    public SessionResourceBuilderPort apiUrl(String apiUrl);

    public SessionResourceBuilderPort downloadUrl(String downloadUrl);

    public SessionResourceBuilderPort uploadUrl(String uploadUrl);

    public SessionResourceBuilderPort eventSourceUrl(String eventSourceUrl);

    public SessionResourceBuilderPort accounts(Map<String, AccountPort> accounts);

    public SessionResourceBuilderPort capabilities(CapabilityPort[] capabilities);

    public SessionResourceBuilderPort primaryAccounts(
            Map<ClassAccountCapabilityPort, String> primaryAccounts);

    public SessionResourceBuilderPort state(String state);

    public SessionResourcePort build();

    public SessionResourceBuilderPort reset();
}