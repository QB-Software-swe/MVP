package it.qbsoftware.business.domain.methodcall.process.set.create;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardCreateIdentityTest {
    @Mock AccountStateRepository accountStateRepository;

    @Mock IdentityChangesTrackerRepository identityChangesTrackerRepository;

    @Mock IdentityRepository identityRepository;

    @Mock IdentityBuilderPort identityBuilderPort;

    @Mock SetErrorEnumPort setErrorEnumPort;

    @Mock SetErrorPort setErrorPort;

    @Mock SetIdentityMethodCallPort setIdentityMethodCallPort;

    @Mock AccountState accountState;

    @Mock CreationIdResolverPort creationIdResolverPort;

    @Mock IdentityChangesTracker identityChangesTracker;

    @InjectMocks StandardCreateIdentity standardCreateIdentity;

    @Test
    public void testCreateWithNullEntryIdentityToCreate() throws AccountNotFoundException {

        when(setIdentityMethodCallPort.getCreate()).thenReturn(null);

        CreatedResult<IdentityPort> result =
                standardCreateIdentity.create(setIdentityMethodCallPort);

        assertNull(result.created());
        assertNull(result.notCreated());
    }

    @Test
    public void testCreateWithNotNullMapIdentityToCreateAndNoExceptions() throws Exception {
        IdentityPort identityPort = mock(IdentityPort.class);
        Map<String, IdentityPort> entryIdentityToCreate = new HashMap<>();
        entryIdentityToCreate.put("key", identityPort);

        when(setIdentityMethodCallPort.getCreate()).thenReturn(entryIdentityToCreate);
        when(setIdentityMethodCallPort.accountId()).thenReturn("accountId");
        when(identityBuilderPort.id(anyString())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.build()).thenReturn(identityPort);
        when(identityBuilderPort.reset()).thenReturn(identityBuilderPort);
        when(identityPort.getBuilder()).thenReturn(identityBuilderPort);
        when(identityChangesTrackerRepository.retrive(any())).thenReturn(identityChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");

        CreatedResult<IdentityPort> result =
                standardCreateIdentity.create(setIdentityMethodCallPort);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());
    }

    @Test
    public void testCreateWithSingletonException() throws Exception {
        IdentityPort identityPort = mock(IdentityPort.class);
        Map<String, IdentityPort> entryIdentityToCreate = new HashMap<>();
        entryIdentityToCreate.put("key", identityPort);

        when(setIdentityMethodCallPort.getCreate()).thenReturn(entryIdentityToCreate);
        when(setIdentityMethodCallPort.accountId()).thenReturn("accountId");
        when(identityBuilderPort.id(anyString())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.build()).thenReturn(identityPort);
        when(identityBuilderPort.reset()).thenReturn(identityBuilderPort);
        when(identityPort.getBuilder()).thenReturn(identityBuilderPort);
        when(setErrorEnumPort.singleton()).thenReturn(setErrorPort);

        doThrow(SetSingletonException.class).doNothing().when(identityRepository).save(any());

        CreatedResult<IdentityPort> result =
                standardCreateIdentity.create(setIdentityMethodCallPort);

        assertNotNull(result);
        assertTrue(result.created().isEmpty());
        assertTrue(result.notCreated().containsKey("key"));
        assertEquals(1, result.notCreated().size());
    }
}
