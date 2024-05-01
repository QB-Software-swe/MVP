package it.qbsoftware.business.domain.methodcall.process.set.destroy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardDestroyEmailTest {

    @Mock
    EmailRepository emailRepository;

    @Mock
    MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    @Mock
    EmailChangesTrackerRepository emailChangesTrackerRepository;

    @Mock
    ThreadChangesTrackerRepository threadChangesTrackerRepository;

    @Mock
    SetErrorEnumPort setErrorEnumPort;

    @Mock
    SetEmailMethodCallPort setEmailMethodCallPort;

    @Mock
    EmailChangesTracker emailChangesTracker;

    @Mock
    ThreadChangesTracker threadChangesTracker;

    @Mock
    MailboxChangesTracker mailboxChangesTracker;

    @Mock
    AccountState accountState;

    @Mock
    AccountStateRepository accountStateRepository;

    @InjectMocks
    StandardDestroyEmail standardDestroyEmail;

    @Test
    public void testDestroyWithNullIdsToDestroy() throws AccountNotFoundException {     
        
        when(setEmailMethodCallPort.getDestroy()).thenReturn(null);

        DestroyedResult result = standardDestroyEmail.destroy(setEmailMethodCallPort);

        assertTrue(result.destroyed().length==0);
        assertTrue(result.notDestroyed().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testDestroyWithNotNullIdsToDestroyAndNoExceptions() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        String[] idsToDestroy = {"1", "2", "3"};
        Map<String, Boolean> mailboxIds = new HashMap<>();
        mailboxIds.put("key", false);

        when(emailPort.getMailboxIds()).thenReturn(mailboxIds);
        when(setEmailMethodCallPort.getDestroy()).thenReturn(idsToDestroy);
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(mailboxChangesTrackerRepository.retrive(any())).thenReturn(mailboxChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        when(emailRepository.destroy(any())).thenReturn(emailPort);

        doNothing().when(accountStateRepository).save(any());

        DestroyedResult result = standardDestroyEmail.destroy(setEmailMethodCallPort);
        
        assertTrue(result.destroyed().length>0);
        assertTrue(result.notDestroyed().isEmpty());
        assertNotNull(result);

    }

    @Test
    public void testDestroyWithSetNotFoundException() throws Exception {    
        String[] idsToDestroy = {"1", "2", "3"};
        Map<String, Boolean> mailboxIds = new HashMap<>();
        mailboxIds.put("key", false);

        when(setEmailMethodCallPort.getDestroy()).thenReturn(idsToDestroy);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        doThrow(SetNotFoundException.class).when(emailRepository).destroy(any());

        DestroyedResult result = standardDestroyEmail.destroy(setEmailMethodCallPort);
        
        assertTrue(result.destroyed().length==0);
        assertNotNull(result);
    }

}
