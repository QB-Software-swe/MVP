package it.qbsoftware.adapters.in.jmaplib.entity;

import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;
import rs.ltt.jmap.common.entity.EmailBodyPart;
import rs.ltt.jmap.common.entity.EmailBodyValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.joda.time.Instant;
import org.junit.Test;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class EmailAdapterTest {
    @Test
    public void testAdaptee() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.adaptee().equals(email));
    }

    @Test
    public void testGetAttachmentsNull() {
        Email email = mock(Email.class);
        when(email.getAttachments()).thenReturn(null);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getAttachments() == null);
    }

    @Test
    public void testGetAttachments() {
        Email emailMock = mock(Email.class);
        
        EmailAdapter adapter = new EmailAdapter(emailMock);
        
        EmailBodyPart attachment1 = mock(EmailBodyPart.class);
        EmailBodyPart attachment2 = mock(EmailBodyPart.class);
        List<EmailBodyPart> attachments = Arrays.asList(attachment1, attachment2);
        
        when(emailMock.getAttachments()).thenReturn(attachments);
        

        List<EmailBodyPartPort> result = adapter.getAttachments();
        
        assertNotNull(result);
        assertEquals(attachments.size(), result.size());
        
        for (EmailBodyPartPort part : result) {
            assertTrue(part instanceof EmailBodyPartAdapter);
        }       
    }

    @Test
    public void testGetBlobId() {
        Email email = mock(Email.class);
        when(email.getBlobId()).thenReturn("123");

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getBlobId().equals("123"));
    }

    @Test
    public void testGetBodyValues() {
        Email email = mock(Email.class);
        EmailAdapter adapter = new EmailAdapter(email);

        Map<String, EmailBodyValue> bodyValues = new HashMap<>();
        bodyValues.put("key1", mock(EmailBodyValue.class));
        bodyValues.put("key2", mock(EmailBodyValue.class));

        when(email.getBodyValues()).thenReturn(bodyValues);

        Map<String, EmailBodyValuePort> result = adapter.getBodyValues();
        assertEquals(bodyValues.size(), result.size());

    }

    @Test
    public void testGetBodyValuesNull() {
        Email email = mock(Email.class);
        when(email.getBodyValues()).thenReturn(null);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getBodyValues() == null);
    }

    @Test
    public void testGetId() {
        Email email = mock(Email.class);
        when(email.getId()).thenReturn("123");

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getId().equals("123"));
    }

    @Test
    public void testGetKeywords() {
        Map<String, Boolean> keywords = new HashMap<>();
        Email email = mock(Email.class);
        when(email.getKeywords()).thenReturn(keywords);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getKeywords().equals(keywords));
    }

    @Test
    public void testGetMailboxIds() {
        Map<String, Boolean> mailboxIds = new HashMap<>();
        Email email = mock(Email.class);
        when(email.getMailboxIds()).thenReturn(mailboxIds);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getMailboxIds().equals(mailboxIds));
    }

    @Test
    public void testGetReceivedAt() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        //TODO: fix test
        Instant instant = Instant.now();
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);
        Field field = Email.class.getDeclaredField("receivedAt");
        field.setAccessible(true);
        field.set(email, instant.toDate().toInstant());
        
        assertEquals(instant, emailAdapter.getReceivedAt());
    }

    @Test
    public void testGetSize() {
        Email email = mock(Email.class);
        when(email.getSize()).thenReturn(123L);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getSize().equals(123L));

    }

    @Test
    public void testGetThreadId() {
        Email email = mock(Email.class);
        when(email.getThreadId()).thenReturn("123");

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert(emailAdapter.getThreadId().equals("123"));
    }

    @Test
    public void testToBuilder() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        when(email.toBuilder()).thenReturn(emailBuilder);

        EmailBuilderPort emailBuilderPort = emailAdapter.toBuilder();

        assert(emailBuilderPort instanceof EmailBuilderAdapter);

    }
}
