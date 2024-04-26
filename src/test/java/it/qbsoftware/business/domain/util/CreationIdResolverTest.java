package it.qbsoftware.business.domain.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CreationIdResolverTest {

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock (extraInterfaces = ResponseInvocationPort.class)
    private SetEmailMethodResponsePort setEmailMethodResponsePort;

    @Mock (extraInterfaces = ResponseInvocationPort.class)
    private SetMailboxMethodResponsePort setMailboxMethodResponsePort;

    @Mock
    private AbstractIdentifiableEntityPort entity;

    @Mock
    private ResponseInvocationPort responseInvocationPort;

    @Test
    public void test(){
        new CreationIdResolver();
    }

    @Test
    public void testResolveIfNecessaryWithNonCreationId() {
        String id = "id";

        String result = CreationIdResolver.resolveIfNecessary(id, previousResponses);

        assertEquals("id", result);
    }

    @Test
    public void testResolveIfNecessaryWithNull() {
        String id = null;

        String result = CreationIdResolver.resolveIfNecessary(id, previousResponses);

        assertEquals(null, result);
    }

    @Test
    public void testResolveWithCreationIdNotFound() {
        String id = "#creationId";

        Map<String, ResponseInvocationPort> map = Map.of("n",responseInvocationPort,"s", (ResponseInvocationPort) setEmailMethodResponsePort);
        
        when(setEmailMethodResponsePort.getCreated()).thenReturn(Map.of("id123", entity));
        when(previousResponses.values()).thenReturn(map.values());

        assertThrows(IllegalArgumentException.class, () -> CreationIdResolver.resolveIfNecessary(id, previousResponses));
    }

    @Test
    public void testResolveWithCreationIdFoundEmail() {
        String id = "#creationId";

        Map<String, ResponseInvocationPort> map = Map.of("s", (ResponseInvocationPort) setEmailMethodResponsePort);
        
        when(setEmailMethodResponsePort.getCreated()).thenReturn(Map.of("creationId", entity));
        when(previousResponses.values()).thenReturn(map.values());
        when(entity.getId()).thenReturn("stringa");

        assertEquals("stringa", CreationIdResolver.resolveIfNecessary(id, previousResponses));
    }

    @Test
    public void testResolveWithCreationIdFoundMailbox(){
        String id = "#creationId";

        Map<String, ResponseInvocationPort> map = Map.of("s", (ResponseInvocationPort)setMailboxMethodResponsePort);
        
        when(setMailboxMethodResponsePort.getCreated()).thenReturn(Map.of("creationId", entity));
        when(previousResponses.values()).thenReturn(map.values());
        when(entity.getId()).thenReturn("stringa");

        assertEquals("stringa", CreationIdResolver.resolveIfNecessary(id, previousResponses));
    }

}