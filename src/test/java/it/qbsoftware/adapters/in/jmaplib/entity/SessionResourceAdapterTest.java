package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;

public class SessionResourceAdapterTest {
    @Test
    public void testAccounts() {
        SessionResource sessionResource = mock(SessionResource.class);
        SessionResourceAdapter sessionResourceAdapter = new SessionResourceAdapter(sessionResource);

        Map<String, Account> accountMap = new HashMap<>();

        when(sessionResource.getAccounts()).thenReturn(accountMap);

        Map<String, AccountPort> accountPortMap = sessionResourceAdapter.accounts();
        assertEquals(accountMap.size(), accountPortMap.size());
    }

    @Test
    public void testAdaptee() {
        SessionResource sessionResource = mock(SessionResource.class);
        SessionResourceAdapter sessionResourceAdapter = new SessionResourceAdapter(sessionResource);

        sessionResourceAdapter.adaptee();
        assertEquals(sessionResource, sessionResourceAdapter.adaptee());
    }

    @Test
    public void testPrimaryAccounts() {
        SessionResource sessionResource = mock(SessionResource.class);
        SessionResourceAdapter sessionResourceAdapter = new SessionResourceAdapter(sessionResource);

        when(sessionResource.getPrimaryAccount(MailAccountCapability.class))
                .thenReturn("mailAccountId");

        Map<ClassAccountCapabilityPort, String> primaryAccounts =
                sessionResourceAdapter.primaryAccounts();
        assertEquals(1, primaryAccounts.size());
    }

    @Test
    public void testState() {
        SessionResource sessionResource = mock(SessionResource.class);
        SessionResourceAdapter sessionResourceAdapter = new SessionResourceAdapter(sessionResource);

        sessionResourceAdapter.state();
        verify(sessionResource).getState();
    }

    @Test
    public void testUsername() {
        SessionResource sessionResource = mock(SessionResource.class);
        SessionResourceAdapter sessionResourceAdapter = new SessionResourceAdapter(sessionResource);

        sessionResourceAdapter.username();
        verify(sessionResource).getUsername();
    }
}
