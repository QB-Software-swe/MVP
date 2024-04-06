package it.qbsoftware.business.ports.in.jmap;

import java.util.Map;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.AccountCapabilityPort;

public interface SessionResourceBuilderPort {
    public SessionResourceBuilderPort username(String name);

    public SessionResourceBuilderPort apiUrl(String apiUrl);

    public SessionResourceBuilderPort downloadUrl(String downloadUrl);

    public SessionResourceBuilderPort uploadUrl(String uploadUrl);

    public SessionResourceBuilderPort eventSourceUrl(String eventSourceUrl);

    public SessionResourceBuilderPort accounts(Map<String, AccountPort> accounts);

    public SessionResourceBuilderPort capabilities(Map<Class<? extends CapabilityPort>, CapabilityPort> capabilities);

    public SessionResourceBuilderPort primaryAccounts(
            Map<Class<? extends AccountCapabilityPort>, String> primaryAccounts);

    public SessionResourcePort build();
}
