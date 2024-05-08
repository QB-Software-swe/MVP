package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import rs.ltt.jmap.common.entity.EmailHeader;

public class EmailHeaderAdapterTest {
    @Test
    public void testAdaptee() {
        EmailHeader emailHeader = mock(EmailHeader.class);
        EmailHeaderAdapter emailHeaderAdapter = new EmailHeaderAdapter(emailHeader);

        assertEquals(emailHeader, emailHeaderAdapter.adaptee());
    }
}
