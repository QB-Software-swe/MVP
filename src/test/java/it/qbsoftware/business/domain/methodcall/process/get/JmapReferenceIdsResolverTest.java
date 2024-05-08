package it.qbsoftware.business.domain.methodcall.process.get;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class JmapReferenceIdsResolverTest {

    @Mock private ResultReferenceResolverPort resultReferenceResolverPort;

    @Mock private GetMethodCallPort getMethodCallPort;

    @Mock private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock private InvocationResultReferencePort invocationResultReferencePort;

    @InjectMocks private JmapReferenceIdsResolver jmapReferenceIdsResolver;

    @Test
    public void testResolveWithIdsReference() throws InvalidResultReferenceExecption {
        String[] expectedIds = new String[] {"id1", "id2"};

        when(getMethodCallPort.getIdsReference()).thenReturn(invocationResultReferencePort);
        when(resultReferenceResolverPort.resolve(invocationResultReferencePort, previousResponses))
                .thenReturn(expectedIds);

        String[] result = jmapReferenceIdsResolver.resolve(getMethodCallPort, previousResponses);

        assertArrayEquals(expectedIds, result);
    }

    @Test
    public void testResolveWithNullIdsReference() throws InvalidResultReferenceExecption {
        String[] expectedIds = new String[] {"id1", "id2"};

        when(getMethodCallPort.getIdsReference()).thenReturn(null);
        when(getMethodCallPort.getIds()).thenReturn(expectedIds);

        String[] result = jmapReferenceIdsResolver.resolve(getMethodCallPort, previousResponses);

        assertArrayEquals(expectedIds, result);
    }

    @Test
    public void testResolveWithInvalidResultReferenceException() {

        when(getMethodCallPort.getIdsReference()).thenReturn(invocationResultReferencePort);
        when(resultReferenceResolverPort.resolve(invocationResultReferencePort, previousResponses))
                .thenThrow(new RuntimeException());

        assertThrows(
                InvalidResultReferenceExecption.class,
                () -> jmapReferenceIdsResolver.resolve(getMethodCallPort, previousResponses));
    }
}
