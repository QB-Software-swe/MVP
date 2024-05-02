package it.qbsoftware.adapters.in.jmaplib.entity;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;

public class EmailBuilderAdapterTest {
    @Test
    public void testAttachment() {
        //TODO: Fix this test
        EmailBodyPartAdapter emailBodyPartAdapter = mock(EmailBodyPartAdapter.class);
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.attachment(emailBodyPartPort);
        verify(emailBuilder).attachment(((EmailBodyPartAdapter) emailBodyPartPort).adaptee());
    }


    @Test
    public void testNullAttachment() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.attachment(null);
        verify(emailBuilder).attachment(null);
    }

    @Test
    public void testBlobId() {
        String blobId = "blobId";
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.blobId(blobId);
        verify(emailBuilder).blobId(blobId);
    }

    @Test
    public void testBuild() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.build();
        verify(emailBuilder).build();
    }

    @Test
    public void testClearAttachments() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.clearAttachments();
        verify(emailBuilder).clearAttachments();
    }

    @Test
    public void testClearMailboxIds() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.clearMailboxIds();
        verify(emailBuilder).clearMailboxIds();
    }

    @Test
    public void testId() {
        String id = "id";
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.id(id);
        verify(emailBuilder).id(id);
    }

    @Test
    public void testKeywords() {
        Map<String, Boolean> keywords = new HashMap<String, Boolean>();
        keywords.put("keyword1", true);
        keywords.put("keyword2", false);

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.keywords(keywords);
        verify(emailBuilder).keywords(keywords);
    }

    @Test
    public void testKeywordsNull() {
        Map<String, Boolean> keywords = null;

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        assertEquals(emailBuilderPort.keywords(keywords), emailBuilderPort);
    }

    @Test
    public void testKeywords2() {
        final String keyword = "keyword";
        final Boolean keywordValue = true;

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.keywords(keyword, keywordValue);
        verify(emailBuilder).keyword(keyword, keywordValue);
    }

    @Test
    public void testConstructor() {
        EmailBuilderAdapter emailBuilderAdapter = new EmailBuilderAdapter();
        assertNotNull(emailBuilderAdapter);
    }


    @Test
    public void testMailboxId() {
        final String mailboxId = "mailboxId";
        final Boolean mailboxIdValue = true;

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.mailboxId(mailboxId, mailboxIdValue);
        verify(emailBuilder).mailboxId(mailboxId, mailboxIdValue);
    }

    @Test
    public void testMailboxIds() {
        Map<String, Boolean> mailboxIds = new HashMap<String, Boolean>();
        mailboxIds.put("mailboxId1", true);
        mailboxIds.put("mailboxId2", false);

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.mailboxIds(mailboxIds);
        verify(emailBuilder).mailboxIds(mailboxIds);
    }

    @Test
    public void testReceivedAt() {
        final Instant instant = Instant.now();
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.receivedAt(instant);
        verify(emailBuilder).receivedAt(instant);
    }

    @Test
    public void testReset() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);
        
        try(MockedStatic<Email> emailStatic = Mockito.mockStatic(Email.class)){
            emailStatic.when(Email::builder).thenReturn(emailBuilder);
            assertEquals(emailBuilderPort.reset(), emailBuilderPort);
            emailStatic.verify(Email::builder);
        }
    }

    @Test
    public void testSize() {
        Long size = 100L;
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.size(size);
        verify(emailBuilder).size(size);
    }

    @Test
    public void testThreadId() {
        final String threadId = "threadId";
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.threadId(threadId);
        verify(emailBuilder).threadId(threadId);
    }
}
