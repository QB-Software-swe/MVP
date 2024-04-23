package it.qbsoftware.adapters.in.jmaplib.entity;

import java.util.Arrays;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Identity.IdentityBuilder;

public class IdentityBuilderAdapter implements IdentityBuilderPort {
    private IdentityBuilder identityBuilder;

    public IdentityBuilderAdapter() {
        identityBuilder = Identity.builder();
    }

    public IdentityBuilderAdapter(final IdentityBuilder identityBuilder) {
        this.identityBuilder = identityBuilder;
    }

    @Override
    public IdentityBuilderPort id(final String id) {
        identityBuilder.id(id);
        return this;
    }

    @Override
    public IdentityBuilderPort name(final String name) {
        identityBuilder.name(name);
        return this;
    }

    @Override
    public IdentityBuilderPort email(final String email) {
        identityBuilder.email(email);
        return this;
    }

    @Override
    public IdentityBuilderPort replyTo(final EmailAddressPort[] emailAddressPorts) {
        identityBuilder.replyTo(Arrays.asList(emailAddressPorts).stream().map(e -> ((EmailAddressAdapter) e).adaptee())
                .toList());
        return this;
    }

    @Override
    public IdentityBuilderPort bcc(final EmailAddressPort[] emailAddressPorts) {
        identityBuilder.bcc(Arrays.asList(emailAddressPorts).stream().map(e -> ((EmailAddressAdapter) e).adaptee())
                .toList());
        return this;
    }

    @Override
    public IdentityBuilderPort textSignature(final String textSignature) {
        identityBuilder.textSignature(textSignature);
        return this;
    }

    @Override
    public IdentityBuilderPort htmlSignature(final String htmlSignature) {
        identityBuilder.htmlSignature(htmlSignature);
        return this;
    }

    @Override
    public IdentityBuilderPort mayDelete(final Boolean mayDelete) {
        identityBuilder.mayDelete(mayDelete);
        return this;
    }

    @Override
    public IdentityPort build() {
        return new IdentityAdapter(identityBuilder.build());
    }

    @Override
    public IdentityBuilderPort reset() {
        identityBuilder = Identity.builder();
        return this;
    }

}
