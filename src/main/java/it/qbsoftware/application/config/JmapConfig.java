package it.qbsoftware.application.config;

import it.qbsoftware.adapters.in.jmaplib.CapabilityAdapter;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;

//TODO: config
public class JmapConfig {
    public static CapabilityPort[] serverCapabilities() {
        return new CapabilityPort[] {
                new CapabilityAdapter(CoreCapability.builder()
                        .maxSizeUpload(50_000_000L)
                        .maxConcurrentUpload(4L)
                        .maxCallsInRequest(16L)
                        .maxObjectsInGet(500L)
                        .maxObjectsInSet(500L)
                        .build()),

                new CapabilityAdapter(MailCapability.builder()
                        .build())
        };
    }
}