package it.qbsoftware.application.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.qbsoftware.adapters.in.jmaplib.entity.CapabilityAdapter;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class JmapConfigTest {
    @InjectMocks private JmapConfig jmapConfig;

    @Test
    public void testServerCapabilities() {

        CapabilityPort[] result = JmapConfig.serverCapabilities();

        assertEquals(2, result.length);
        assertTrue(result[0] instanceof CapabilityAdapter);
        assertTrue(result[1] instanceof CapabilityAdapter);
    }
}
