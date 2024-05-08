package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponsePort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse.SetIdentityMethodResponseBuilder;

public class SetIdentityMethodResponseBuilderAdapter
        implements SetIdentityMethodResponseBuilderPort {
    private SetIdentityMethodResponseBuilder setIdentityMethodResponseBuilder =
            SetIdentityMethodResponse.builder();

    @Override
    public SetIdentityMethodResponseBuilderPort accountId(final String state) {
        setIdentityMethodResponseBuilder.accountId(state);
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort oldState(final String oldState) {
        setIdentityMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort newState(final String newState) {
        setIdentityMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort created(final Map<String, IdentityPort> created) {
        if (created != null) {
            setIdentityMethodResponseBuilder.created(
                    created.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((IdentityAdapter) e.getValue()).adaptee())));
        } else {
            setIdentityMethodResponseBuilder.created(null);
        }
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort updated(final Map<String, IdentityPort> updated) {
        if (updated != null) {
            setIdentityMethodResponseBuilder.updated(
                    updated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((IdentityAdapter) e.getValue()).adaptee())));
        } else {
            setIdentityMethodResponseBuilder.updated(null);
        }
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort destroyed(final String[] idDestroyed) {
        setIdentityMethodResponseBuilder.destroyed(idDestroyed);
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort notCreated(
            final Map<String, SetErrorPort> notCreated) {
        if (notCreated != null) {
            setIdentityMethodResponseBuilder.notCreated(
                    notCreated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setIdentityMethodResponseBuilder.notCreated(null);
        }
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort notUpdated(
            final Map<String, SetErrorPort> notUpdated) {
        if (notUpdated != null) {
            setIdentityMethodResponseBuilder.notUpdated(
                    notUpdated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setIdentityMethodResponseBuilder.notUpdated(null);
        }
        return this;
    }

    @Override
    public SetIdentityMethodResponseBuilderPort notDestroyed(
            final Map<String, SetErrorPort> notDestroyed) {
        if (notDestroyed != null) {
            setIdentityMethodResponseBuilder.notDestroyed(
                    notDestroyed.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setIdentityMethodResponseBuilder.notDestroyed(null);
        }
        return this;
    }

    @Override
    public SetIdentityMethodResponsePort build() {
        return new SetIdentityMethodResponseAdapter(setIdentityMethodResponseBuilder.build());
    }

    @Override
    public SetIdentityMethodResponseBuilderPort reset() {
        setIdentityMethodResponseBuilder = SetIdentityMethodResponse.builder();
        return this;
    }
}
