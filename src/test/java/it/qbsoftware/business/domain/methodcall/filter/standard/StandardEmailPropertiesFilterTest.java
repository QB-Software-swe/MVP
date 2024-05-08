package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailHeaderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardEmailPropertiesFilterTest {

    @Mock private EmailBuilderPort emailBuilderPort;

    @Mock private EmailBodyPartPort emailBodyPartPort;

    @Mock private EmailPort emailPort;

    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};

        StandardEmailPropertiesFilter standardEmailPropertiesFilter =
                new StandardEmailPropertiesFilter();

        EmailPort[] result = standardEmailPropertiesFilter.filter(emails, null, null);

        assertArrayEquals(emails, result);
    }

    @Test
    public void testFilterWithNotNullValidProperties() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};
        String[] properties =
                new String[] {
                    "blobId",
                    "threadId",
                    "mailboxIds",
                    "keywords",
                    "size",
                    "receivedAt",
                    "messageId",
                    "inReplyTo",
                    "references",
                    "sender",
                    "from",
                    "to",
                    "cc",
                    "bcc",
                    "replyTo",
                    "subject",
                    "bodyValues",
                    "textBody",
                    "htmlBody",
                    "attachments",
                    "sentAt",
                    "bodyStructure",
                    "header",
                    "id"
                };
        List<EmailBodyPartPort> listEmailBodyPartPort = new ArrayList<>();
        List<String> listString = new ArrayList<>();
        List<EmailAddressPort> listEmailAddressPort = new ArrayList<>();
        List<EmailHeaderPort> listEmailHeaderPort = new ArrayList<>();
        Map<String, Boolean> keywords = new HashMap<>();
        Map<String, Boolean> mailboxIds = new HashMap<>();
        Map<String, EmailBodyValuePort> bodyValues = new HashMap<>();

        when(emailPort.getId()).thenReturn("id");
        when(emailPort.getBlobId()).thenReturn("blobId");
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getMailboxIds()).thenReturn(mailboxIds);
        when(emailPort.getKeywords()).thenReturn(keywords);
        when(emailPort.getAttachments()).thenReturn(listEmailBodyPartPort);
        when(emailPort.getBodyValues()).thenReturn(bodyValues);
        when(emailPort.getReceivedAt()).thenReturn(Instant.now());
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailPort.getMessageId()).thenReturn(listString);
        when(emailPort.getInReplyTo()).thenReturn(listString);
        when(emailPort.getReferences()).thenReturn(listString);
        when(emailPort.getReplyTo()).thenReturn(listEmailAddressPort);
        when(emailPort.getSender()).thenReturn(listEmailAddressPort);
        when(emailPort.getFrom()).thenReturn(listEmailAddressPort);
        when(emailPort.getTo()).thenReturn(listEmailAddressPort);
        when(emailPort.getCc()).thenReturn(listEmailAddressPort);
        when(emailPort.getBcc()).thenReturn(listEmailAddressPort);
        when(emailPort.getTextBody()).thenReturn(listEmailBodyPartPort);
        when(emailPort.getHtmlBody()).thenReturn(listEmailBodyPartPort);
        when(emailPort.getSentAt()).thenReturn(OffsetDateTime.now());
        when(emailPort.getBodyStructure()).thenReturn(emailBodyPartPort);
        when(emailPort.getHeaders()).thenReturn(listEmailHeaderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        when(emailBuilderPort.id(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.attachments(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.keywords(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.messageId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.inReplyTo(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.references(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.sender(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.from(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.to(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.cc(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.bcc(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.replyTo(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.subject(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.bodyValues(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.textBody(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.htmlBody(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.attachments(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.sentAt(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.bodyStructure(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.headers(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.build()).thenReturn(emailPort);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);

        StandardEmailPropertiesFilter standardEmailPropertiesFilter =
                new StandardEmailPropertiesFilter();

        EmailPort[] result = standardEmailPropertiesFilter.filter(emails, properties, null);

        assertArrayEquals(emails, result);
    }

    @Test
    public void testFilterWithNullValidProperties() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};
        String[] properties =
                new String[] {
                    "blobId",
                    "threadId",
                    "mailboxIds",
                    "keywords",
                    "size",
                    "receivedAt",
                    "messageId",
                    "inReplyTo",
                    "references",
                    "sender",
                    "from",
                    "to",
                    "cc",
                    "bcc",
                    "replyTo",
                    "subject",
                    "bodyValues",
                    "textBody",
                    "htmlBody",
                    "attachments",
                    "sentAt",
                    "bodyStructure",
                    "header",
                    "id"
                };
        List<String> listString = new ArrayList<>();
        Map<String, Boolean> keywords = new HashMap<>();
        Map<String, Boolean> mailboxIds = new HashMap<>();

        when(emailPort.getId()).thenReturn("id");
        when(emailPort.getBlobId()).thenReturn("blobId");
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getMailboxIds()).thenReturn(mailboxIds);
        when(emailPort.getKeywords()).thenReturn(keywords);
        when(emailPort.getAttachments()).thenReturn(null);
        when(emailPort.getBodyValues()).thenReturn(null);
        when(emailPort.getReceivedAt()).thenReturn(Instant.now());
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailPort.getMessageId()).thenReturn(listString);
        when(emailPort.getInReplyTo()).thenReturn(null);
        when(emailPort.getReferences()).thenReturn(null);
        when(emailPort.getSender()).thenReturn(null);
        when(emailPort.getFrom()).thenReturn(null);
        when(emailPort.getTo()).thenReturn(null);
        when(emailPort.getCc()).thenReturn(null);
        when(emailPort.getBcc()).thenReturn(null);
        when(emailPort.getReplyTo()).thenReturn(null);
        when(emailPort.getTextBody()).thenReturn(null);
        when(emailPort.getHtmlBody()).thenReturn(null);
        when(emailPort.getSentAt()).thenReturn(null);
        when(emailPort.getBodyStructure()).thenReturn(null);
        when(emailPort.getHeaders()).thenReturn(null);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        when(emailBuilderPort.id(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.keywords(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.messageId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.subject(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.build()).thenReturn(emailPort);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);

        StandardEmailPropertiesFilter standardEmailPropertiesFilter =
                new StandardEmailPropertiesFilter();

        EmailPort[] result = standardEmailPropertiesFilter.filter(emails, properties, null);

        assertArrayEquals(emails, result);
    }

    @Test
    public void testFilterWithInvalidArgumentsException() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};
        String[] properties =
                new String[] {
                    "blobId", "InvalidProperties", "mailboxIds", "keywords", "size", "receivedAt"
                };

        when(emailPort.getId()).thenReturn("id");
        when(emailPort.getBlobId()).thenReturn("blobId");
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(any())).thenReturn(emailBuilderPort);

        StandardEmailPropertiesFilter standardEmailPropertiesFilter =
                new StandardEmailPropertiesFilter();

        assertThrows(
                InvalidArgumentsException.class,
                () -> standardEmailPropertiesFilter.filter(emails, properties, null));
    }
}
