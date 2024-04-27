package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardEmailSubmissionPropertiesFilterTest {

    @Mock
    private EmailSubmissionBuilderPort emailSubmissionBuilderPort;

    @Mock
    private EmailSubmissionPort emailSubmissionPort;

    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        EmailSubmissionPort[] emailSubmissionPorts = new EmailSubmissionPort[] {emailSubmissionPort};
        StandardEmailSubmissionPropertiesFilter standardEmailSubmissionPropertiesFilter = new StandardEmailSubmissionPropertiesFilter(emailSubmissionBuilderPort);

        EmailSubmissionPort[] result = standardEmailSubmissionPropertiesFilter.filter(emailSubmissionPorts, null);

        assertArrayEquals(emailSubmissionPorts, result); 
    }

    @Test
    public void testFilterWithValidProperties() throws InvalidArgumentsException {
        EmailSubmissionPort[] emailSubmissionPorts = new EmailSubmissionPort[] {emailSubmissionPort};
        String[] properties = new String[] {"blobId", "threadId", "mailboxIds", "keywords", "size", "receivedAt"};

        StandardEmailSubmissionPropertiesFilter standardEmailSubmissionPropertiesFilter = new StandardEmailSubmissionPropertiesFilter(emailSubmissionBuilderPort);

        EmailSubmissionPort[] result = standardEmailSubmissionPropertiesFilter.filter(emailSubmissionPorts, properties);

        assertArrayEquals(emailSubmissionPorts, result);
    }


}
