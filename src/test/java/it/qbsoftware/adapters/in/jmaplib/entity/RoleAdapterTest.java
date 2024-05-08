package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import org.junit.Test;
import rs.ltt.jmap.common.entity.Role;

public class RoleAdapterTest {
    @Test
    public void testGetRole() {
        Role role = mock(Role.class);
        RoleAdapter roleAdapter = new RoleAdapter(role);

        assertEquals(roleAdapter.getRole(), role);
    }

    @Test
    public void testValueOf() {
        String name = "ALL";
        RolePort rolePort = new RoleAdapter(Role.valueOf(name));
        rolePort.valueOf(name);
        assertNotNull(rolePort);
    }
}
