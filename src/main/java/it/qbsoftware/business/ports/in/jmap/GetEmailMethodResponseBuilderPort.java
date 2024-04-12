package it.qbsoftware.business.ports.in.jmap;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public interface GetEmailMethodResponseBuilderPort {

    public GetEmailMethodResponseBuilderPort list(EmailPort[] emailList);

    public GetEmailMethodResponseBuilderPort state(final String state);

    public GetEmailMethodResponsePort build();

    public GetEmailMethodResponseBuilderPort reset();
}
