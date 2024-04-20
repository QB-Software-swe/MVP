package it.qbsoftware.business.domain.util;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;

public class CreationIdResolver {

    public static String resolveIfNecessary(final String id,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        return isACreationId(id) ? resolve(id, previousResponses) : id;
    }

    // FIXME
    private static String resolve(String creationId,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        // FIXME: (precond) creationId != null
        final String strippedId = creationId.substring(1);
        for (final ResponseInvocationPort invocation : previousResponses.values()) {
            final AbstractIdentifiableEntityPort entity = switch (invocation) {
                case SetEmailMethodResponsePort setEmailMethodResponsePort:
                    yield setEmailMethodResponsePort.getCreated().get(strippedId);

                case SetMailboxMethodResponsePort setMailboxMethodResponsePort:
                    yield setMailboxMethodResponsePort.getCreated().get(strippedId);

                default:
                    yield null; // TODO: controllare il livello di completezza
            };

            if (entity != null) {
                return entity.getId();
            }
        }

        throw new IllegalArgumentException("Creation id " + strippedId + " not found");
    }

    private static boolean isACreationId(final String id) {
        return id != null && id.charAt(0) == '#';
    }
}
