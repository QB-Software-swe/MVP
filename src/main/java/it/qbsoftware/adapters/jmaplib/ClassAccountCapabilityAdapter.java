package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import rs.ltt.jmap.common.entity.AccountCapability;

public class ClassAccountCapabilityAdapter implements ClassAccountCapabilityPort {
    private Class<? extends AccountCapability> classAccountCapability;

    public ClassAccountCapabilityAdapter(final Class<? extends AccountCapability> classAccountCapability) {
        this.classAccountCapability = classAccountCapability;
    }

    public Class<? extends AccountCapability> classAccountCapability() {
        return classAccountCapability;
    }
}
