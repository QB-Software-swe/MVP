package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import rs.ltt.jmap.common.entity.Capability;

public class CapabilityAdapter implements CapabilityPort {
    Capability capability;

    public CapabilityAdapter(Capability capability) {
        this.capability = capability;
    }

    public Capability adaptee() {
        return this.capability;
    }
}
