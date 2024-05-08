package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailHeaderPort;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;
import rs.ltt.jmap.common.entity.EmailAddress;
import rs.ltt.jmap.common.entity.EmailBodyPart;
import rs.ltt.jmap.common.entity.EmailBodyValue;
import rs.ltt.jmap.common.entity.EmailHeader;

public class EmailAdapterTest {
    @Test
    public void testAdaptee() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.adaptee().equals(email));
    }

    @Test
    public void testGetAttachmentsNull() {
        Email email = mock(Email.class);
        when(email.getAttachments()).thenReturn(null);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.getAttachments() == null);
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
        assert (emailAdapter.getBlobId().equals("123"));
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
        assert (emailAdapter.getBodyValues() == null);
    }

    @Test
    public void testGetId() {
        Email email = mock(Email.class);
        when(email.getId()).thenReturn("123");

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.getId().equals("123"));
    }

    @Test
    public void testGetKeywords() {
        Map<String, Boolean> keywords = new HashMap<>();
        Email email = mock(Email.class);
        when(email.getKeywords()).thenReturn(keywords);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.getKeywords().equals(keywords));
    }

    @Test
    public void testGetMailboxIds() {
        Map<String, Boolean> mailboxIds = new HashMap<>();
        Email email = mock(Email.class);
        when(email.getMailboxIds()).thenReturn(mailboxIds);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.getMailboxIds().equals(mailboxIds));
    }

    @Test
    public void testGetReceivedAt() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        emailAdapter.getReceivedAt();
        verify(email).getReceivedAt();
    }

    @Test
    public void testGetSize() {
        Email email = mock(Email.class);
        when(email.getSize()).thenReturn(123L);

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.getSize().equals(123L));
    }

    @Test
    public void testGetThreadId() {
        Email email = mock(Email.class);
        when(email.getThreadId()).thenReturn("123");

        EmailAdapter emailAdapter = new EmailAdapter(email);
        assert (emailAdapter.getThreadId().equals("123"));
    }

    @Test
    public void testToBuilder() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        when(email.toBuilder()).thenReturn(emailBuilder);

        EmailBuilderPort emailBuilderPort = emailAdapter.toBuilder();

        assert (emailBuilderPort instanceof EmailBuilderAdapter);
    }

    @Test
    public void testGetSubject() {
        String subject = "testSubject";
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getSubject()).thenReturn(subject);

        assertEquals(subject, emailAdapter.getSubject());
    }

    @Test
    public void testGetMessageId() {
        List<String> messageId = Arrays.asList("testMessageId");
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getMessageId()).thenReturn(messageId);

        assertEquals(messageId, emailAdapter.getMessageId());
    }

    @Test
    public void testGetInReplyTo() {
        List<String> inReplyTo = Arrays.asList("testInReplyTo");
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getInReplyTo()).thenReturn(inReplyTo);

        assertEquals(inReplyTo, emailAdapter.getInReplyTo());
    }

    @Test
    public void testGetReferences() {
        List<String> references = Arrays.asList("testReferences");
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getReferences()).thenReturn(references);

        assertEquals(references, emailAdapter.getReferences());
    }

    @Test
    public void testGetSenderNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailAddress> sender = mock(List.class);
        when(email.getSender()).thenReturn(sender);

        List<EmailAddressPort> result = emailAdapter.getSender();
        assertNotNull(result);
    }

    @Test
    public void testGetSenderNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getSender()).thenReturn(null);

        List<EmailAddressPort> result = emailAdapter.getSender();
        assertEquals(null, result);
    }

    @Test
    public void testGetFromNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailAddress> from = mock(List.class);
        when(email.getFrom()).thenReturn(from);

        List<EmailAddressPort> result = emailAdapter.getFrom();
        assertNotNull(result);
    }

    @Test
    public void testGetFromNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getFrom()).thenReturn(null);

        List<EmailAddressPort> result = emailAdapter.getFrom();
        assertEquals(null, result);
    }

    @Test
    public void testGetToNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailAddress> to = mock(List.class);
        when(email.getTo()).thenReturn(to);

        List<EmailAddressPort> result = emailAdapter.getTo();
        assertNotNull(result);
    }

    @Test
    public void testGetToNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getTo()).thenReturn(null);

        List<EmailAddressPort> result = emailAdapter.getTo();
        assertEquals(null, result);
    }

    @Test
    public void testGetCcNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailAddress> cc = mock(List.class);
        when(email.getCc()).thenReturn(cc);

        List<EmailAddressPort> result = emailAdapter.getCc();
        assertNotNull(result);
    }

    @Test
    public void testGetCcNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getCc()).thenReturn(null);

        List<EmailAddressPort> result = emailAdapter.getCc();
        assertEquals(null, result);
    }

    @Test
    public void testGetBccNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailAddress> bcc = mock(List.class);
        when(email.getBcc()).thenReturn(bcc);

        List<EmailAddressPort> result = emailAdapter.getBcc();
        assertNotNull(result);
    }

    @Test
    public void testGetBccNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getBcc()).thenReturn(null);

        List<EmailAddressPort> result = emailAdapter.getBcc();
        assertEquals(null, result);
    }

    @Test
    public void testGetTextBodyNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailBodyPart> textBody = mock(List.class);
        when(email.getTextBody()).thenReturn(textBody);

        List<EmailBodyPartPort> result = emailAdapter.getTextBody();
        assertNotNull(result);
    }

    @Test
    public void testGetTextBodyNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getTextBody()).thenReturn(null);

        List<EmailBodyPartPort> result = emailAdapter.getTextBody();
        assertEquals(null, result);
    }

    @Test
    public void testGetHtmlBodyNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailBodyPart> htmlBody = mock(List.class);
        when(email.getHtmlBody()).thenReturn(htmlBody);

        List<EmailBodyPartPort> result = emailAdapter.getHtmlBody();
        assertNotNull(result);
    }

    @Test
    public void testGetHtmlBodyNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getHtmlBody()).thenReturn(null);

        List<EmailBodyPartPort> result = emailAdapter.getHtmlBody();
        assertEquals(null, result);
    }

    @Test
    public void testGetSentAt() {
        OffsetDateTime instant = OffsetDateTime.now();
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getSentAt()).thenReturn(instant);
        emailAdapter.getSentAt();
        verify(email).getSentAt();
    }

    @Test
    public void testGetBodyStructureNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        EmailBodyPart bodyStructure = mock(EmailBodyPart.class);
        when(email.getBodyStructure()).thenReturn(bodyStructure);

        EmailBodyPartPort result = emailAdapter.getBodyStructure();
        assertNotNull(result);
    }

    @Test
    public void testGetBodyStructureNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getBodyStructure()).thenReturn(null);

        EmailBodyPartPort result = emailAdapter.getBodyStructure();
        assertEquals(null, result);
    }

    @Test
    public void testGetHeadersNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailHeader> headers = mock(List.class);
        when(email.getHeaders()).thenReturn(headers);

        List<EmailHeaderPort> result = emailAdapter.getHeaders();
        assertNotNull(result);
    }

    @Test
    public void testGetHeadersNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getHeaders()).thenReturn(null);

        List<EmailHeaderPort> result = emailAdapter.getHeaders();
        assertEquals(null, result);
    }

    @Test
    public void testGetReplyToNotNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        @SuppressWarnings("unchecked")
        List<EmailAddress> replyTo = mock(List.class);
        when(email.getReplyTo()).thenReturn(replyTo);

        List<EmailAddressPort> result = emailAdapter.getReplyTo();
        assertNotNull(result);
    }

    @Test
    public void testGetReplyToNull() {
        Email email = mock(Email.class);
        EmailAdapter emailAdapter = new EmailAdapter(email);

        when(email.getReplyTo()).thenReturn(null);

        List<EmailAddressPort> result = emailAdapter.getReplyTo();
        assertEquals(null, result);
    }
}
