package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardEmailPropertiesFilterTest {

    @Mock
    private EmailBuilderPort emailBuilderPort;

    @Mock
    private EmailPort emailPort;


    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};

        StandardEmailPropertiesFilter standardEmailPropertiesFilter = new StandardEmailPropertiesFilter();

        EmailPort[] result = standardEmailPropertiesFilter.filter(emails, null, null);

        assertArrayEquals(emails, result);
    }

    @Test
    public void testFilterWithValidProperties() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};
        String[] properties = new String[] {"blobId", "threadId", "mailboxIds", "keywords", "size", "receivedAt"};
        Map<String, Boolean> keywords = new HashMap<>();
        Map<String, Boolean> mailboxIds = new HashMap<>();

        when(emailPort.getId()).thenReturn("id");
        when(emailPort.getBlobId()).thenReturn("blobId");
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getMailboxIds()).thenReturn(mailboxIds);
        when(emailPort.getKeywords()).thenReturn(keywords);
        when(emailPort.getSize()).thenReturn(100L);
        when(emailPort.getReceivedAt()).thenReturn(Instant.now());

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.keywords(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.build()).thenReturn(emailPort);

        StandardEmailPropertiesFilter standardEmailPropertiesFilter = new StandardEmailPropertiesFilter();

        EmailPort[] result = standardEmailPropertiesFilter.filter(emails, properties, null);

        assertArrayEquals(emails, result);
    }

    @Test
    public void testFilterWithInvalidArgumentsException() throws InvalidArgumentsException {
        EmailPort[] emails = new EmailPort[] {emailPort};
        String[] properties = new String[] {"blobId", "InvalidProperties", "mailboxIds", "keywords", "size", "receivedAt"};
        Map<String, Boolean> keywords = new HashMap<>();
        Map<String, Boolean> mailboxIds = new HashMap<>();

        when(emailPort.getId()).thenReturn("id");
        when(emailPort.getBlobId()).thenReturn("blobId");
        when(emailPort.getMailboxIds()).thenReturn(mailboxIds);
        when(emailPort.getKeywords()).thenReturn(keywords);
        when(emailPort.getSize()).thenReturn(100L);
        when(emailPort.getReceivedAt()).thenReturn(Instant.now());

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
  
        StandardEmailPropertiesFilter standardEmailPropertiesFilter = new StandardEmailPropertiesFilter();

        //TODO: utilizzare questa se si implementa la throw
        //assertThrows(InvalidArgumentsException.class, () -> standardEmailPropertiesFilter.filter(emails, properties, null));

        EmailPort[] result = standardEmailPropertiesFilter.filter(emails, properties, null);

        assertArrayEquals(emails, result);

    }
}
