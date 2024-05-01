package it.qbsoftware.business.ports.in.jmap.entity;

public interface EmailBodyPartBuilderPort {

    public EmailBodyPartBuilderPort blobId(final String string);

    public EmailBodyPartBuilderPort charset(final String charset);

    public EmailBodyPartBuilderPort type(final String type);

    public EmailBodyPartBuilderPort name(final String name);

    public EmailBodyPartBuilderPort size(final Long size);

    public EmailBodyPartPort build();

    public EmailBodyPartBuilderPort reset();
}
