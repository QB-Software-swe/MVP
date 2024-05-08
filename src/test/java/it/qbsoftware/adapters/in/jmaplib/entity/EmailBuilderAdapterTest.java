package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailHeaderPort;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Email.EmailBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EmailBuilderAdapterTest {

    @Test
    public void testAttachment() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);
        EmailBodyPartAdapter emailBodyPartAdapter = mock(EmailBodyPartAdapter.class);

        emailBuilderPort.attachment(emailBodyPartAdapter);
        verify(emailBuilder).attachment(any());
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

        try (MockedStatic<Email> emailStatic = Mockito.mockStatic(Email.class)) {
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

    @Test
    public void testMessageId() {
        List<String> messageId = List.of("messageId1", "messageId2");

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.messageId(messageId);
        verify(emailBuilder).messageId(messageId);
    }

    @Test
    public void testInReplyTo() {
        List<String> inReplyTo = List.of("inReplyTo1", "inReplyTo2");

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.inReplyTo(inReplyTo);
        verify(emailBuilder).inReplyTo(inReplyTo);
    }

    @Test
    public void testReferences() {
        List<String> references = List.of("references1", "references2");

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.references(references);
        verify(emailBuilder).references(references);
    }

    @Test
    public void testSentAt() {
        OffsetDateTime sentAt = OffsetDateTime.now();

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.sentAt(sentAt);
        verify(emailBuilder).sentAt(sentAt);
    }

    @Test
    public void testHasAttachment() {
        Boolean attachment = true;

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.hasAttachment(attachment);
        verify(emailBuilder).hasAttachment(attachment);
    }

    @Test
    public void testSender() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailAddressPort> sender = mock(List.class);

        emailBuilderPort.sender(sender);
        verify(emailBuilder)
                .sender(sender.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList());
    }

    @Test
    public void testFrom() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailAddressPort> from = mock(List.class);

        emailBuilderPort.from(from);
        verify(emailBuilder)
                .from(from.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList());
    }

    @Test
    public void testTo() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailAddressPort> to = mock(List.class);

        emailBuilderPort.to(to);
        verify(emailBuilder).to(to.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList());
    }

    @Test
    public void testCc() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailAddressPort> cc = mock(List.class);

        emailBuilderPort.cc(cc);
        verify(emailBuilder).cc(cc.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList());
    }

    @Test
    public void testBcc() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailAddressPort> bcc = mock(List.class);

        emailBuilderPort.bcc(bcc);
        verify(emailBuilder)
                .bcc(bcc.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList());
    }

    @Test
    public void testReplyTo() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailAddressPort> replyTo = mock(List.class);

        emailBuilderPort.replyTo(replyTo);
        verify(emailBuilder)
                .replyTo(replyTo.stream().map(e -> ((EmailAddressAdapter) e).adaptee()).toList());
    }

    @Test
    public void testSubject() {
        String subject = "subject";

        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        emailBuilderPort.subject(subject);
        verify(emailBuilder).subject(subject);
    }

    @Test
    public void testBodyValues() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        Map<String, EmailBodyValuePort> bodyValues = new HashMap<String, EmailBodyValuePort>();

        emailBuilderPort.bodyValues(bodyValues);
        verify(emailBuilder)
                .bodyValues(
                        bodyValues.entrySet().stream()
                                .collect(
                                        HashMap::new,
                                        (m, e) ->
                                                m.put(
                                                        e.getKey(),
                                                        ((EmailBodyValueAdapter) e.getValue())
                                                                .adaptee()),
                                        HashMap::putAll));
    }

    @Test
    public void testTextBody() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailBodyPartPort> textBody = mock(List.class);

        emailBuilderPort.textBody(textBody);
        verify(emailBuilder)
                .textBody(
                        textBody.stream().map(e -> ((EmailBodyPartAdapter) e).adaptee()).toList());
    }

    @Test
    public void testHtmlBody() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailBodyPartPort> htmlBody = mock(List.class);

        emailBuilderPort.htmlBody(htmlBody);
        verify(emailBuilder)
                .htmlBody(
                        htmlBody.stream().map(e -> ((EmailBodyPartAdapter) e).adaptee()).toList());
    }

    @Test
    public void testAttachments() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailBodyPartPort> attachments = mock(List.class);

        emailBuilderPort.attachments(attachments);
        verify(emailBuilder)
                .attachments(
                        attachments.stream()
                                .map(e -> ((EmailBodyPartAdapter) e).adaptee())
                                .toList());
    }

    @Test
    public void testBodyStructure() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        EmailBodyPartAdapter emailBodyPart = mock(EmailBodyPartAdapter.class);

        emailBuilderPort.bodyStructure(emailBodyPart);
        verify(emailBuilder).bodyStructure(emailBodyPart.adaptee());
    }

    @Test
    public void testHeaders() {
        EmailBuilder emailBuilder = mock(EmailBuilder.class);
        EmailBuilderPort emailBuilderPort = new EmailBuilderAdapter(emailBuilder);

        @SuppressWarnings("unchecked")
        List<EmailHeaderPort> headers = mock(List.class);

        emailBuilderPort.headers(headers);
        verify(emailBuilder)
                .headers(headers.stream().map(e -> ((EmailHeaderAdapter) e).adaptee()).toList());
    }
}
