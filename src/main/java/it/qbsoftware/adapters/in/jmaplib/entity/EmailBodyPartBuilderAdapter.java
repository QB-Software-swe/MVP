package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import rs.ltt.jmap.common.entity.EmailBodyPart;
import rs.ltt.jmap.common.entity.EmailBodyPart.EmailBodyPartBuilder;

public class EmailBodyPartBuilderAdapter implements EmailBodyPartBuilderPort {
    private EmailBodyPartBuilder emailBodyPartBuilder = EmailBodyPart.builder();

    @Override
    public EmailBodyPartBuilderPort blobId(final String blobId) {
        emailBodyPartBuilder.blobId(blobId);
        return this;
    }

    @Override
    public EmailBodyPartBuilderPort charset(final String charset) {
        emailBodyPartBuilder.charset(charset);
        return this;
    }

    @Override
    public EmailBodyPartBuilderPort type(final String type) {
        emailBodyPartBuilder.type(type);
        return this;
    }

    @Override
    public EmailBodyPartBuilderPort name(final String name) {
        emailBodyPartBuilder.name(name);
        return this;
    }

    @Override
    public EmailBodyPartBuilderPort size(final Long size) {
        emailBodyPartBuilder.size(size);
        return this;
    }

    @Override
    public EmailBodyPartPort build() {
        return new EmailBodyPartAdapter(emailBodyPartBuilder.build());
    }

    @Override
    public EmailBodyPartBuilderPort reset() {
        emailBodyPartBuilder = EmailBodyPart.builder();
        return this;
    }

}
