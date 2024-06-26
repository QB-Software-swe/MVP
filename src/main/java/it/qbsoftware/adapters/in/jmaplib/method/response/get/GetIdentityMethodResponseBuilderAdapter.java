package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort;
import java.util.stream.Stream;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse.GetIdentityMethodResponseBuilder;

public class GetIdentityMethodResponseBuilderAdapter
        implements GetIdentityMethodResponseBuilderPort {
    GetIdentityMethodResponseBuilder getIdentityMethodResponseBuilder;

    public GetIdentityMethodResponseBuilderAdapter() {
        this.getIdentityMethodResponseBuilder = GetIdentityMethodResponse.builder();
    }

    @Override
    public GetIdentityMethodResponseBuilderPort list(IdentityPort[] identityPorts) {
        getIdentityMethodResponseBuilder.list(
                Stream.of(identityPorts)
                        .map(identityPort -> ((IdentityAdapter) identityPort).adaptee())
                        .toArray(Identity[]::new));
        return this;
    }

    @Override
    public GetIdentityMethodResponseBuilderPort notFound(String[] identityIsdsNotFound) {
        getIdentityMethodResponseBuilder.notFound(identityIsdsNotFound);
        return this;
    }

    @Override
    public GetIdentityMethodResponseBuilderPort state(String state) {
        getIdentityMethodResponseBuilder.state(state);
        return this;
    }

    @Override
    public GetIdentityMethodResponsePort build() {
        return new GetIdentityMethodResponseAdapter(getIdentityMethodResponseBuilder.build());
    }

    @Override
    public GetIdentityMethodResponseBuilderPort reset() {
        this.getIdentityMethodResponseBuilder = GetIdentityMethodResponse.builder();
        return this;
    }
}
