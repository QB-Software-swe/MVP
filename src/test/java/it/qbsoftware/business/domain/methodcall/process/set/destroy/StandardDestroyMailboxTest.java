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
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardDestroyMailboxTest {

    @Mock
    AccountStateRepository accountStateRepository;

    @Mock
    AccountState accountState;

    @Mock 
    MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    @Mock
    MailboxChangesTracker mailboxChangesTracker;

    @Mock
    MailboxRepository mailboxRepository;

    @Mock
    SetErrorEnumPort setErrorEnumPort;

    @Mock
    SetMailboxMethodCallPort setMailboxMethodCallPort;

    @InjectMocks
    StandardDestroyMailbox standardDestroyMailbox;


    @Test
    public void testDestroyWithNullIdsToDestroy() throws AccountNotFoundException {     
        
        when(setMailboxMethodCallPort.getDestroy()).thenReturn(null);

        DestroyedResult result = standardDestroyMailbox.destroy(setMailboxMethodCallPort);

        assertTrue(result.destroyed().length==0);
        assertTrue(result.notDestroyed().isEmpty());
        assertNotNull(result);
    }


    @Test
    public void testDestroyWithNotNullIdsToDestroyAndNoExceptions() throws AccountNotFoundException { 
        String[] idsToDestroy = {"1", "2", "3"};
        Map<String, Boolean> mailboxIds = new HashMap<>();
        mailboxIds.put("key", false);

        when(setMailboxMethodCallPort.getDestroy()).thenReturn(idsToDestroy);
        when(mailboxChangesTrackerRepository.retrive(any())).thenReturn(mailboxChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        when(setMailboxMethodCallPort.accountId()).thenReturn("accountId");

        DestroyedResult result = standardDestroyMailbox.destroy(setMailboxMethodCallPort);

        assertTrue(result.destroyed().length>0);
        assertTrue(result.notDestroyed().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testDestroyWithSetNotFoundException() throws Exception {  
        String[] idsToDestroy = {"1", "2", "3"};
        Map<String, Boolean> mailboxIds = new HashMap<>();
        mailboxIds.put("key", false);

        when(setMailboxMethodCallPort.getDestroy()).thenReturn(idsToDestroy);
        doThrow(SetNotFoundException.class).when(mailboxRepository).destroy(any());

        DestroyedResult result = standardDestroyMailbox.destroy(setMailboxMethodCallPort);
        
        assertTrue(result.destroyed().length==0);
        assertNotNull(result);
    }

}
