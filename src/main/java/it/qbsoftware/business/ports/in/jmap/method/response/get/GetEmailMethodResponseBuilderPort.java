package it.qbsoftware.business.ports.in.jmap.method.response.get;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface GetEmailMethodResponseBuilderPort {

    public GetEmailMethodResponseBuilderPort list(EmailPort[] emailList);

    public GetEmailMethodResponseBuilderPort notFound(String[] notFound);

    public GetEmailMethodResponseBuilderPort state(final String state);

    public GetEmailMethodResponsePort build();

    public GetEmailMethodResponseBuilderPort reset();
}
