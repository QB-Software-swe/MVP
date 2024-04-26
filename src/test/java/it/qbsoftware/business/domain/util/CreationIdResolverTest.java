package it.qbsoftware.business.domain.util;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Answers.values;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CreationIdResolverTest {

    //TODO TEST: finire il test

    /*@Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private SetEmailMethodResponsePort setEmailMethodResponsePort;

    @Mock
    private AbstractIdentifiableEntityPort entity;

    
    @Test
    public void testResolveIfNecessaryWithCreationId() {
        // Mocking previousResponses
        Map<String, ResponseInvocationPort> map = new HashMap<>();
        
        when(setEmailMethodResponsePort.getCreated()).thenReturn(Map.of("id123", mock(AbstractIdentifiableEntityPort.class)));
        //map.put("key", (ResponseInvocationPort) setEmailMethodResponsePort);
        when(previousResponses.values()).thenReturn(map.values());

        // Calling the method under test
        String resolvedId = CreationIdResolver.resolveIfNecessary("id123", previousResponses);

        // Verifying that the correct method is called and the resolved id is returned
        assertEquals("id123", resolvedId);
    }

    @Test
    public void testResolveIfNecessaryWithNonCreationId() {
        String id = "id";

        String result = CreationIdResolver.resolveIfNecessary(id, previousResponses);

        assertEquals("id", result);
    }

    @Test
    public void testResolveWithCreationIdNotFound() {
        String id = "#creationId";

        Map<String, ResponseInvocationPort> map = new HashMap<>();
        
        when(setEmailMethodResponsePort.getCreated()).thenReturn(Map.of("id123", entity));
        when(previousResponses.values()).thenReturn(map.values());

        assertThrows(IllegalArgumentException.class, () -> CreationIdResolver.resolveIfNecessary(id, previousResponses));
    }

    @Test
    public void testIsACreationId() {
        //assertTrue(CreationIdResolver.isACreationId("#creationId"));
        //assertFalse(CreationIdResolver.isACreationId("creationId"));
        //assertFalse(CreationIdResolver.isACreationId(null));
    }*/
}