package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
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
