package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

public class SetMailboxMethodResponseBuilderAdapter implements SetMailboxMethodResponseBuilderPort {
    private SetMailboxMethodResponse.SetMailboxMethodResponseBuilder
            setMailboxMethodResponseBuilder;

    public SetMailboxMethodResponseBuilderAdapter() {
        this.setMailboxMethodResponseBuilder = SetMailboxMethodResponse.builder();
    }

    @Override
    public SetMailboxMethodResponseBuilderPort accountId(final String state) {
        setMailboxMethodResponseBuilder.accountId(state);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort oldState(final String oldState) {
        setMailboxMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort newState(final String newState) {
        setMailboxMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort created(final Map<String, MailboxPort> created) {
        setMailboxMethodResponseBuilder.created(
                created.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((MailboxAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort updated(final Map<String, MailboxPort> updated) {
        setMailboxMethodResponseBuilder.updated(
                updated.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> ((MailboxAdapter) e.getValue()).adaptee())));
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort destroyed(final String[] idDestroyed) {
        setMailboxMethodResponseBuilder.destroyed(idDestroyed);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort notCreated(
            final Map<String, SetErrorPort> notCreated) {
        if (notCreated != null) {
            setMailboxMethodResponseBuilder.notCreated(
                    notCreated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setMailboxMethodResponseBuilder.notCreated(null);
        }
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort notUpdated(
            final Map<String, SetErrorPort> notUpdated) {
        if (notUpdated != null) {
            setMailboxMethodResponseBuilder.notUpdated(
                    notUpdated.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setMailboxMethodResponseBuilder.notUpdated(null);
        }
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort notDestroyed(
            final Map<String, SetErrorPort> notDestroyed) {
        if (notDestroyed != null) {
            setMailboxMethodResponseBuilder.notDestroyed(
                    notDestroyed.entrySet().stream()
                            .collect(
                                    Collectors.toMap(
                                            e -> e.getKey(),
                                            e -> ((SetErrorAdapter) e.getValue()).adaptee())));
        } else {
            setMailboxMethodResponseBuilder.notDestroyed(null);
        }
        return this;
    }

    @Override
    public SetMailboxMethodResponsePort build() {
        return new SetMailboxMethodResponseAdapter(setMailboxMethodResponseBuilder.build());
    }

    @Override
    public SetMailboxMethodResponseBuilderPort reset() {
        setMailboxMethodResponseBuilder = SetMailboxMethodResponse.builder();
        return this;
    }
}
