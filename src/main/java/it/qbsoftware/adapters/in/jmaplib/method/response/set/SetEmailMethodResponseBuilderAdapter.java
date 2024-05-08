package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse.SetEmailMethodResponseBuilder;

public class SetEmailMethodResponseBuilderAdapter implements SetEmailMethodResponseBuilderPort {
    private SetEmailMethodResponseBuilder setEmailMethodResponseBuilder =
            SetEmailMethodResponse.builder();

    @Override
    public SetEmailMethodResponseBuilderPort accountId(final String id) {
        setEmailMethodResponseBuilder.accountId(id);
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort oldState(final String oldState) {
        setEmailMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort newState(final String newState) {
        setEmailMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort created(final Map<String, EmailPort> created) {
        if (created != null) {
            setEmailMethodResponseBuilder.created(
                    created.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((EmailAdapter) e.getValue()).adaptee())));
        } else {
            setEmailMethodResponseBuilder.created(null);
        }
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort updated(final Map<String, EmailPort> updated) {
        if (updated != null) {
            setEmailMethodResponseBuilder.updated(
                    updated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((EmailAdapter) e.getValue()).adaptee())));
        } else {
            setEmailMethodResponseBuilder.updated(null);
        }
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort destroyed(final String[] idDestroyed) {
        setEmailMethodResponseBuilder.destroyed(idDestroyed);
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort notCreated(
            final Map<String, SetErrorPort> notCreated) {
        if (notCreated != null) {
            setEmailMethodResponseBuilder.notCreated(
                    notCreated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setEmailMethodResponseBuilder.notCreated(null);
        }
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort notUpdated(
            final Map<String, SetErrorPort> notUpdated) {
        if (notUpdated != null) {
            setEmailMethodResponseBuilder.notUpdated(
                    notUpdated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setEmailMethodResponseBuilder.notUpdated(null);
        }
        return this;
    }

    @Override
    public SetEmailMethodResponseBuilderPort notDestroyed(
            final Map<String, SetErrorPort> notDestroyed) {
        if (notDestroyed != null) {
            setEmailMethodResponseBuilder.notDestroyed(
                    notDestroyed.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setEmailMethodResponseBuilder.notDestroyed(null);
        }
        return this;
    }

    @Override
    public SetEmailMethodResponsePort build() {
        return new SetEmailMethodResponseAdapter(setEmailMethodResponseBuilder.build());
    }

    @Override
    public SetEmailMethodResponseBuilderPort reset() {
        setEmailMethodResponseBuilder = SetEmailMethodResponse.builder();
        return this;
    }
}
