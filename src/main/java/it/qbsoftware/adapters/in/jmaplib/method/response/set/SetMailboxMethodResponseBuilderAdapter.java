package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import java.util.Map;
import java.util.stream.Collectors;

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

public class SetMailboxMethodResponseBuilderAdapter implements SetMailboxMethodResponseBuilderPort {
    private SetMailboxMethodResponse.SetMailboxMethodResponseBuilder setMailboxMethodResponseBuilder;

    public SetMailboxMethodResponseBuilderAdapter() {
        this.setMailboxMethodResponseBuilder = SetMailboxMethodResponse.builder();
    }

    @Override
    public SetMailboxMethodResponseBuilderPort accountId(String state) {
        setMailboxMethodResponseBuilder.accountId(state);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort oldState(String oldState) {
        setMailboxMethodResponseBuilder.oldState(oldState);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort newState(String newState) {
        setMailboxMethodResponseBuilder.newState(newState);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort created(Map<String, MailboxPort> created) {
        setMailboxMethodResponseBuilder.created(created.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> ((MailboxAdapter) e).adaptee())));
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort updated(Map<String, MailboxPort> updated) {
        setMailboxMethodResponseBuilder.updated(updated.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> ((MailboxAdapter) e).adaptee())));
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort destroyed(String[] idDestroyed) {
        setMailboxMethodResponseBuilder.destroyed(idDestroyed);
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort notCreated(Map<String, SetErrorPort> notCreated) {
        setMailboxMethodResponseBuilder.notCreated(notCreated.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> ((SetErrorAdapter) e).adaptee())));
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort notUpdated(Map<String, SetErrorPort> notUpdated) {
        setMailboxMethodResponseBuilder.notUpdated(notUpdated.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> ((SetErrorAdapter) e).adaptee())));
        return this;
    }

    @Override
    public SetMailboxMethodResponseBuilderPort notDestroyed(Map<String, SetErrorPort> notDestroyed) {
        setMailboxMethodResponseBuilder.notDestroyed(notDestroyed.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> ((SetErrorAdapter) e).adaptee())));
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
