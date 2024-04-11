package it.qbsoftware.business.domain;

import it.qbsoftware.business.ports.in.jmap.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;
//FIXME: (grave) rompe l'archiettura esangonale
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;

public class CreationIdResolver {

    public static String resolveIfNecessary(final String id,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        return isCreationId(id) ? resolve(id, previousResponses) : id;
    }

    // FIXME
    private static String resolve(String creationId,
            ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
        // FIXME: (precond) creationId != null
        final String strippedId = creationId.substring(1);
        for (final ResponseInvocationPort invocation : previousResponses.values()) {
            final AbstractIdentifiableEntity entity = switch (invocation) {
                case SetEmailMethodResponsePort setEmailMethodResponsePort:
                    var x = setEmailMethodResponsePort.getCreated().get(strippedId);
                    yield x;

                case SetMailboxMethodResponsePort setMailboxMethodResponsePort:
                    var y = setMailboxMethodResponsePort.getCreated().get(strippedId);
                    yield y;

                default:
                    yield null;
            };

            if (entity != null) {
                return entity.getId();
            }
        }

        throw new IllegalArgumentException("Creation if " + strippedId + " not found");
    }

    private static boolean isCreationId(final String id) {
        return id != null && id.charAt(0) == '#';
    }
}
