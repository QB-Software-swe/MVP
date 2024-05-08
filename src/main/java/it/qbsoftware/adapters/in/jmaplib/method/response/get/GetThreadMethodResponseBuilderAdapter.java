package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;
import java.util.Arrays;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.entity.Thread;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse.GetThreadMethodResponseBuilder;

public class GetThreadMethodResponseBuilderAdapter implements GetThreadMethodResponseBuilderPort {
    private GetThreadMethodResponseBuilder getThreadMethodResponseBuilder;

    public GetThreadMethodResponseBuilderAdapter(
            final GetThreadMethodResponseBuilder getThreadMethodResponseBuilder) {
        this.getThreadMethodResponseBuilder = getThreadMethodResponseBuilder;
    }

    public GetThreadMethodResponseBuilderAdapter() {
        this.getThreadMethodResponseBuilder = GetThreadMethodResponse.builder();
    }

    @Override
    public GetThreadMethodResponseBuilderPort list(final ThreadPort[] threads) {
        getThreadMethodResponseBuilder.list(
                Arrays.asList(threads).stream()
                        .map(t -> ((ThreadAdapter) t).adaptee())
                        .collect(Collectors.toList())
                        .toArray(Thread[]::new));
        return this;
    }

    @Override
    public GetThreadMethodResponseBuilderPort notFound(final String[] ids) {
        getThreadMethodResponseBuilder.notFound(ids);
        return this;
    }

    @Override
    public GetThreadMethodResponseBuilderPort state(final String state) {
        getThreadMethodResponseBuilder.state(state);
        return this;
    }

    @Override
    public GetThreadMethodResponsePort build() {
        return new GetThreadMethodResponseAdapter(getThreadMethodResponseBuilder.build());
    }

    @Override
    public GetThreadMethodResponseBuilderPort reset() {
        getThreadMethodResponseBuilder = GetThreadMethodResponse.builder();
        return this;
    }
}
