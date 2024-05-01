package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import rs.ltt.jmap.common.entity.EmailAddress;

public class EmailAddressAdapterTest {
    @Test
    public void testAdaptee() {
        EmailAddress emailAddress = mock(EmailAddress.class);
        EmailAddressAdapter emailAddressAdapter = new EmailAddressAdapter(emailAddress);

        assertEquals(emailAddress, emailAddressAdapter.adaptee());
    }
}
