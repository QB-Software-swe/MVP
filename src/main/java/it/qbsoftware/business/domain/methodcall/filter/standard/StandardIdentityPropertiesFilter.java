package it.qbsoftware.business.domain.methodcall.filter.standard;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.IdentityPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import java.util.ArrayList;
import java.util.List;

public class StandardIdentityPropertiesFilter implements IdentityPropertiesFilter {
    final IdentityBuilderPort identityBuilderPort;

    public StandardIdentityPropertiesFilter(final IdentityBuilderPort identityBuilderPort) {
        this.identityBuilderPort = identityBuilderPort.reset();
    }

    @Override
    public IdentityPort[] filter(final IdentityPort[] identityPorts, final String[] properties)
            throws InvalidArgumentsException {
        if (properties == null) {
            return identityPorts;
        }

        final List<IdentityPort> filtredIdenities = new ArrayList<IdentityPort>();
        for (final IdentityPort identityPort : identityPorts) {
            filtredIdenities.add(filterIdentity(identityPort, properties));
        }

        return filtredIdenities.toArray(IdentityPort[]::new);
    }

    private IdentityPort filterIdentity(final IdentityPort identityPort, final String[] properties)
            throws InvalidArgumentsException {
        IdentityBuilderPort identityBuilder = identityBuilderPort.reset();
        identityBuilder.id(identityPort.getId());

        for (final String property : properties) {
            identityBuilder =
                    switch (property) {
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

                        case "htmlSignature":
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
