package it.qbsoftware.business.domain.util.get;

import java.util.ArrayList;
import java.util.List;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;

public class GetIdentityPropertiesFilter implements GetEntityPropertiesFilter<IdentityPort> {
    final IdentityBuilderPort identityBuilderPort;

    public GetIdentityPropertiesFilter(final IdentityBuilderPort identityBuilderPort) {
        this.identityBuilderPort = identityBuilderPort;
    }

    @Override
    public IdentityPort[] filter(IdentityPort[] entities, String[] properties) throws InvalidArgumentsException {
        if (properties == null) {
            return entities;
        }

        List<IdentityPort> filtredIdenities = new ArrayList<IdentityPort>();
        for (IdentityPort identityPort : entities) {
            filtredIdenities.add(identityFilter(identityPort, properties));
        }

        return filtredIdenities.toArray(IdentityPort[]::new);
    }

    private IdentityPort identityFilter(final IdentityPort identityPort, final String[] properties)
            throws InvalidArgumentsException {
        IdentityBuilderPort identityBuilder = identityBuilderPort.reset();
        identityBuilder.id(identityPort.getId());

        for (final String property : properties) {
            identityBuilder = switch (property) {
                case "name":
                    yield identityBuilder.name(identityPort.getName());

                case "email":
                    yield identityBuilder.email(identityPort.getEmail());

                case "replyTo":
                    yield identityBuilder.replyTo(identityPort.getReplyTo());

                case "bcc":
                    yield identityBuilder.bcc(identityPort.getBcc());

                case "textSignature":
                    yield identityBuilder.textSignature(identityPort.getTextSignature());

                case "htmlSingature":
                    yield identityBuilder.htmlSignature(identityPort.getHtmlSignature());

                case "mayDelete":
                    yield identityBuilder.mayDelete(identityPort.getMayDelete());

                default:
                    throw new InvalidArgumentsException();
            };
        }

        return identityBuilder.build();
    }

}
