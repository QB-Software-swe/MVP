package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import rs.ltt.jmap.common.entity.EmailBodyPart;

public class EmailBodyPartAdapterTest {
    @Test
    public void testGetCharset() {
        String charset = "UTF-8";
        EmailBodyPart emailBodyPart = mock(EmailBodyPart.class);
        EmailBodyPartAdapter emailBodyPartAdapter = new EmailBodyPartAdapter(emailBodyPart);
        when(emailBodyPart.getCharset()).thenReturn(charset);

        assertEquals(charset, emailBodyPartAdapter.getCharset());
    }

    @Test
    public void testGetName() {
        String name = "name";
        EmailBodyPart emailBodyPart = mock(EmailBodyPart.class);
        EmailBodyPartAdapter emailBodyPartAdapter = new EmailBodyPartAdapter(emailBodyPart);
        when(emailBodyPart.getName()).thenReturn(name);
        
        assertEquals(name, emailBodyPartAdapter.getName());
    }

    @Test
    public void testGetPartId() {
        String partId = "partId";
        EmailBodyPart emailBodyPart = mock(EmailBodyPart.class);
        EmailBodyPartAdapter emailBodyPartAdapter = new EmailBodyPartAdapter(emailBodyPart);
        when(emailBodyPart.getPartId()).thenReturn(partId);

        assertEquals(partId, emailBodyPartAdapter.getPartId());
    }

    @Test
    public void testGetSize() {
        Long size = 100L;
        EmailBodyPart emailBodyPart = mock(EmailBodyPart.class);
        EmailBodyPartAdapter emailBodyPartAdapter = new EmailBodyPartAdapter(emailBodyPart);
        when(emailBodyPart.getSize()).thenReturn(size);

        assertEquals(size, emailBodyPartAdapter.getSize());
    }

    @Test
    public void testGetType() {
        String type = "type";
        EmailBodyPart emailBodyPart = mock(EmailBodyPart.class);
        EmailBodyPartAdapter emailBodyPartAdapter = new EmailBodyPartAdapter(emailBodyPart);
        when(emailBodyPart.getType()).thenReturn(type);

        assertEquals(type, emailBodyPartAdapter.getType());
    }
}
