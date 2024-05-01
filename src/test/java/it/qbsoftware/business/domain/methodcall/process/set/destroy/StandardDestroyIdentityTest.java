package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardDestroyIdentityTest {

    @Mock
    AccountStateRepository accountStateRepository;

    @Mock
    AccountState accountState;

    @Mock 
    IdentityChangesTrackerRepository identityChangesTrackerRepository;

    @Mock
    IdentityChangesTracker identityChangesTracker;

    @Mock
    IdentityRepository identityRepository;

    @Mock
    SetErrorEnumPort setErrorEnumPort;

    @Mock
    SetIdentityMethodCallPort setIdentityMethodCallPort;

    @InjectMocks
    StandardDestroyIdentity standardDestroyIdentity;

    @Test
    public void testDestroyWithNullIdsToDestroy() throws AccountNotFoundException {     
        
        when(setIdentityMethodCallPort.getDestroy()).thenReturn(null);

        DestroyedResult result = standardDestroyIdentity.destroy(setIdentityMethodCallPort);

        assertTrue(result.destroyed().length==0);
        assertTrue(result.notDestroyed().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testDestroyWithNotNullIdsToDestroyAndNoExceptions() throws AccountNotFoundException { 
        String[] idsToDestroy = {"1", "2", "3"};
        Map<String, Boolean> mailboxIds = new HashMap<>();
        mailboxIds.put("key", false);


        when(setIdentityMethodCallPort.getDestroy()).thenReturn(idsToDestroy);
        when(identityChangesTrackerRepository.retrive(any())).thenReturn(identityChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");

        DestroyedResult result = standardDestroyIdentity.destroy(setIdentityMethodCallPort);

        assertTrue(result.destroyed().length>0);
        assertTrue(result.notDestroyed().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testDestroyWithSetNotFoundException() throws Exception {  
        String[] idsToDestroy = {"1", "2", "3"};
        Map<String, Boolean> mailboxIds = new HashMap<>();
        mailboxIds.put("key", false);

        when(setIdentityMethodCallPort.getDestroy()).thenReturn(idsToDestroy);
        doThrow(SetNotFoundException.class).when(identityRepository).destroy(any());

        DestroyedResult result = standardDestroyIdentity.destroy(setIdentityMethodCallPort);
        
        assertTrue(result.destroyed().length==0);
        assertNotNull(result);
    }

}
