package it.qbsoftware.adapters.in.jmaplib;

import java.util.stream.Stream;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponsePort;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse.GetEmailMethodResponseBuilder;

public class GetEmailMethodResponseBuilderAdapter implements GetEmailMethodResponseBuilderPort {
    GetEmailMethodResponseBuilder getEmailMethodResponseBuilder;

    public GetEmailMethodResponseBuilderAdapter() {
        this.getEmailMethodResponseBuilder = GetEmailMethodResponse.builder();
    }

    @Override
    public GetEmailMethodResponseBuilderPort list(EmailPort[] emailList) {
        getEmailMethodResponseBuilder
                .list(Stream.of(emailList).map(emailPort -> ((EmailAdapter) emailPort).email).toArray(Email[]::new));
        return this;
    }

    @Override
    public GetEmailMethodResponseBuilderPort state(String state) {
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
    public GetEmailMethodResponseBuilderPort notFound(String[] notFound) {
        this.getEmailMethodResponseBuilder.notFound(notFound);
        return this;
    }

}
