package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import rs.ltt.jmap.common.entity.EmailBodyValue;

public class EmailBodyValueAdapterTest {
    @Test
    public void testConstructor() {
        EmailBodyValue emailBodyValue = mock(EmailBodyValue.class);
        EmailBodyValueAdapter emailBodyValueAdapter = new EmailBodyValueAdapter(emailBodyValue);
        
        assertEquals(emailBodyValue, emailBodyValueAdapter.adaptee());
    }
}
