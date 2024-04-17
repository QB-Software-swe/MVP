package it.qbsoftware.adapters.in.jmaplib.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.entity.Capability;

public class SessionResourceBuilderAdapter implements SessionResourceBuilderPort {
    SessionResourceBuilder sessionResourceBuilder;

    public SessionResourceBuilderAdapter() {
        this.sessionResourceBuilder = SessionResource.builder();
    }

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
    public SessionResourceBuilderPort capabilities(CapabilityPort[] capabilities) {
        sessionResourceBuilder.capabilities(
                Arrays.asList(capabilities).stream().map(c -> ((CapabilityAdapter) c).adaptee())
                        .collect(Collectors.toMap(Capability::getClass, c -> c)));
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

    @Override
    public SessionResourceBuilderPort reset() {
        this.sessionResourceBuilder = SessionResource.builder();
        return this;
    }

}
