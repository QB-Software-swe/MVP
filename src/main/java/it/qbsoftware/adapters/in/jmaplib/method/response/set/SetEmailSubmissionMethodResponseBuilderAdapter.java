package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse;
import rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse.SetEmailSubmissionMethodResponseBuilder;

public class SetEmailSubmissionMethodResponseBuilderAdapter
        implements SetEmailSubmissionMethodResponseBuilderPort {
    private SetEmailSubmissionMethodResponseBuilder setEmailSubmissionMethodResponseBuilder =
            SetEmailSubmissionMethodResponse.builder();

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort accountId(final String accountId) {
        setEmailSubmissionMethodResponseBuilder.accountId(accountId);
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort oldState(final String oldState) {
        setEmailSubmissionMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort newState(final String newState) {
        setEmailSubmissionMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort created(
            final Map<String, EmailSubmissionPort> created) {
        setEmailSubmissionMethodResponseBuilder.created(
                created.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((EmailSubmissionAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort updated(
            final Map<String, EmailSubmissionPort> updated) {
        setEmailSubmissionMethodResponseBuilder.updated(
                updated.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((EmailSubmissionAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort destroyed(final String[] idDestroyed) {
        setEmailSubmissionMethodResponseBuilder.destroyed(idDestroyed);
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort notCreated(
            final Map<String, SetErrorPort> notCreated) {
        setEmailSubmissionMethodResponseBuilder.notCreated(
                notCreated.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort notUpdated(
            final Map<String, SetErrorPort> notUpdated) {
        setEmailSubmissionMethodResponseBuilder.notUpdated(
                notUpdated.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort notDestroyed(
            final Map<String, SetErrorPort> notDestroyed) {
        setEmailSubmissionMethodResponseBuilder.notDestroyed(
                notDestroyed.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetEmailSubmissionMethodResponsePort build() {
        return new SetEmailSubmissionMethodResponseAdapter(
                setEmailSubmissionMethodResponseBuilder.build());
    }

    @Override
    public SetEmailSubmissionMethodResponseBuilderPort reset() {
        setEmailSubmissionMethodResponseBuilder = SetEmailSubmissionMethodResponse.builder();
        return this;
    }
}
