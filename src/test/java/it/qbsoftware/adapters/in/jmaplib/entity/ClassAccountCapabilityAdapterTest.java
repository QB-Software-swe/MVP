package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import rs.ltt.jmap.common.entity.AccountCapability;

public class ClassAccountCapabilityAdapterTest {

    @Test
    public void testClassAccountCapability() {

        ClassAccountCapabilityAdapter adapter =
                new ClassAccountCapabilityAdapter(AccountCapability.class);

        Class<? extends AccountCapability> result = adapter.classAccountCapability();
        assertNotNull(result);
        assertEquals(AccountCapability.class, result);
    }
}
