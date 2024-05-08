package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import rs.ltt.jmap.common.entity.Capability;

public class CapabilityAdapterTest {
    @Test
    public void adaptee() {
        Capability capability = mock(Capability.class);
        CapabilityAdapter capabilityAdapter = new CapabilityAdapter(capability);
        assertEquals(capability, capabilityAdapter.adaptee());
    }
}
