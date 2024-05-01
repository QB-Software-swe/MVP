package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardThreadPropertiesFilterTest {

    @Mock
    private ThreadBuilderPort threadBuilderPort;

    @Mock
    private ThreadPort threadPort;

    @InjectMocks
    private StandardThreadPropertiesFilter standardThreadPropertiesFilter;

    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        ThreadPort[] threadPorts = new ThreadPort[] {threadPort};

        ThreadPort[] result = standardThreadPropertiesFilter.filter(threadPorts, null);

        assertArrayEquals(threadPorts, result); 
    }

    @Test
    public void testFilterWithValidProperties() throws InvalidArgumentsException {
        ThreadPort[] threadPorts = new ThreadPort[] {threadPort};
        String[] properties = new String[] {"blobId", "threadId", "mailboxIds", "keywords", "size", "receivedAt"};

        ThreadPort[] result = standardThreadPropertiesFilter.filter(threadPorts, properties);

        assertArrayEquals(threadPorts, result);
    }


}
