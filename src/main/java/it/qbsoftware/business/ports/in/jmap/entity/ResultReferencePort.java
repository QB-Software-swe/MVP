package it.qbsoftware.business.ports.in.jmap.entity;

import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

//FIXME: RINOMINARE PORTA IN RESULTREFERENCERESOLVER

public interface ResultReferencePort {
    public String[] resolve(final InvocationResultReferencePort resultReference,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses);
}
