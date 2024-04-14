package it.qbsoftware.business.ports.in.jmap.entity;

public interface IdentityBuilderPort {

    public IdentityBuilderPort id(final String id);

    public IdentityBuilderPort name(final String name);

    public IdentityBuilderPort email(final String email);

    public IdentityBuilderPort replyTo(final EmailAddressPort[] emailAddressPorts);

    public IdentityBuilderPort bcc(final EmailAddressPort[] emailAddressPorts);

    public IdentityBuilderPort textSignature(final String textSignature);

    public IdentityBuilderPort htmlSignature(final String htmlSignature);

    public IdentityBuilderPort mayDelete(final Boolean mayDelete);

    public IdentityPort build();

    public IdentityBuilderPort reset();
}
