package it.qbsoftware.adapters.jmaplib;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

import it.qbsoftware.business.ports.in.jmap.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.capabilities.AccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.AccountCapability;
import rs.ltt.jmap.common.entity.Capability;

public class SessionResourceBuilderAdapter implements SessionResourceBuilderPort {
    SessionResourceBuilder sessionResourceBuilder;

    @Override
    public SessionResourceBuilderPort username(String name) {
        sessionResourceBuilder.username(name);
        return this;
    }

    @Override
    public SessionResourceBuilderPort apiUrl(String apiUrl) {
        sessionResourceBuilder.apiUrl(apiUrl);
        return this;
    }

    @Override
    public SessionResourceBuilderPort downloadUrl(String downloadUrl) {
        sessionResourceBuilder.downloadUrl(downloadUrl);
        return this;
    }

    @Override
    public SessionResourceBuilderPort uploadUrl(String uploadUrl) {
        sessionResourceBuilder.uploadUrl(uploadUrl);
        return this;
    }

    @Override
    public SessionResourceBuilderPort eventSourceUrl(String eventSourceUrl) {
        sessionResourceBuilder.eventSourceUrl(eventSourceUrl);
        return this;
    }

    @Override
    public SessionResourceBuilderPort accounts(Map<String, AccountPort> accounts) {
        sessionResourceBuilder.accounts(
                accounts.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Entry::getKey, e -> ((AccountAdapter) e.getValue()).account)));
        return this;
    }

    @Override
    public SessionResourceBuilderPort capabilities(Map<Class<? extends CapabilityPort>, CapabilityPort> capabilities) {
        sessionResourceBuilder.capabilities(
                capabilities.entrySet()
                        .stream()
                        .map(e -> ((CapabilityAdapter) e.getValue()).capability)
                        .collect(Collectors.toMap(Capability::getClass, c -> c)));
        // FIXME: check della logica di questo stream
        return this;
    }

    @Override
    public SessionResourceBuilderPort primaryAccounts(
            Map<ClassAccountCapabilityPort, String> primaryAccounts) {

        sessionResourceBuilder.primaryAccounts(
                primaryAccounts.entrySet().stream().collect(Collectors.toMap(
                        entrySet -> ((ClassAccountCapabilityAdapter) entrySet.getKey()).classAccountCapability(),
                        entrySet -> entrySet.getValue())));
        return this;
    }

    @Override
    public SessionResourceBuilderPort state(String state) {
        sessionResourceBuilder.state(state);
        return this;
    }

    @Override
    public SessionResourcePort build() {
        return new SessionResourceAdapter(sessionResourceBuilder.build());
    }

}
