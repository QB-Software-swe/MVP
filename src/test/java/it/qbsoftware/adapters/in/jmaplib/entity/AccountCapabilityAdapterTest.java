package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import rs.ltt.jmap.common.entity.AccountCapability;

public class AccountCapabilityAdapterTest {

    @Test
    public void testBuild() {

        AccountCapability accountCapabilityMock = mock(AccountCapability.class);
        AccountCapabilityAdapter adapter = new AccountCapabilityAdapter(accountCapabilityMock);
        assertEquals(accountCapabilityMock, adapter.accountCapability);
    }
}
