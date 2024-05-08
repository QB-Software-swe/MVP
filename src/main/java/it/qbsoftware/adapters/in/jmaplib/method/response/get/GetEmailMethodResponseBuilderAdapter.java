package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponsePort;
import java.util.stream.Stream;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse.GetEmailMethodResponseBuilder;

public class GetEmailMethodResponseBuilderAdapter implements GetEmailMethodResponseBuilderPort {
    private GetEmailMethodResponseBuilder getEmailMethodResponseBuilder;

    public GetEmailMethodResponseBuilderAdapter() {
        this.getEmailMethodResponseBuilder = GetEmailMethodResponse.builder();
    }

    @Override
    public GetEmailMethodResponseBuilderPort list(final EmailPort[] emailList) {
        if (emailList != null) {
            getEmailMethodResponseBuilder.list(
                    Stream.of(emailList)
                            .map(emailPort -> ((EmailAdapter) emailPort).adaptee())
                            .toArray(Email[]::new));
        } else {
            getEmailMethodResponseBuilder.list(null);
        }
        return this;
    }

    @Override
    public GetEmailMethodResponseBuilderPort state(final String state) {
        getEmailMethodResponseBuilder.state(state);
        return this;
    }

    @Override
    public GetEmailMethodResponsePort build() {
        return new GetEmailMethodResponseAdapter(getEmailMethodResponseBuilder.build());
    }

    @Override
    public GetEmailMethodResponseBuilderPort reset() {
        this.getEmailMethodResponseBuilder = GetEmailMethodResponse.builder();
        return this;
    }

    @Override
    public GetEmailMethodResponseBuilderPort notFound(final String[] notFound) {
        this.getEmailMethodResponseBuilder.notFound(notFound);
        return this;
    }
}
