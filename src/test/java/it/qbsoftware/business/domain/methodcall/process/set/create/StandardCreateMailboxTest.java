package it.qbsoftware.business.domain.methodcall.process.set.create;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.google.inject.Inject;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.Mailbox.MailboxBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardCreateMailboxTest {

    @Mock
    AccountStateRepository accountStateRepository;

    @Mock
    MailboxChangesTrackerRepository mailboxChangesTrackerRepository;    

    @Mock
    MailboxRepository mailboxRepository;

    @Mock
    SetErrorEnumPort setErrorEnumPort;

    @Mock
    SetErrorPort setErrorPort;

    @Mock
    SetMailboxMethodCallPort setMailboxMethodCallPort;

    @Mock
    AccountState accountState;

    @Mock
    MailboxChangesTracker mailboxChangesTracker;

    @Mock
    MailboxBuilderPort mailboxBuilderPort;

    @InjectMocks
    StandardCreateMailbox standardCreateMailbox;

    
    @Test
    public void testCreateWithNullMapMailboxToCreate() throws AccountNotFoundException {     
        
        when(setMailboxMethodCallPort.getCreate()).thenReturn(null);

        CreatedResult<MailboxPort> result = standardCreateMailbox.create(setMailboxMethodCallPort);

        assertTrue(result.created().isEmpty());
        assertTrue(result.notCreated().isEmpty());
    }

    @Test
    public void testCreateWithNotNullMapMailboxToCreateAndNoExceptions() throws Exception {     
        MailboxPort mailboxPort = mock(MailboxPort.class);
        Map<String, MailboxPort> mapMailboxToCreate = new HashMap<>();
        mapMailboxToCreate.put("key", mailboxPort);

        when(setMailboxMethodCallPort.getCreate()).thenReturn(mapMailboxToCreate);
        when(mailboxBuilderPort.reset()).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.id(anyString())).thenReturn(mailboxBuilderPort);
        when(setMailboxMethodCallPort.accountId()).thenReturn("accountId");
        when(mailboxChangesTrackerRepository.retrive(any())).thenReturn(mailboxChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        when(mailboxPort.getBuilder()).thenReturn(mailboxBuilderPort);

        doNothing().when(mailboxRepository).save(any());
        doNothing().when(accountStateRepository).save(any());

        CreatedResult<MailboxPort> result = standardCreateMailbox.create(setMailboxMethodCallPort);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());
    }

    @Test
    public void testCreateWithSingletonException() throws Exception {     
        MailboxPort mailboxPort = mock(MailboxPort.class);
        Map<String, MailboxPort> entryMailboxToCreate = new HashMap<>();
        entryMailboxToCreate.put("key", mailboxPort);

        when(setMailboxMethodCallPort.getCreate()).thenReturn(entryMailboxToCreate);
        when(setMailboxMethodCallPort.accountId()).thenReturn("accountId");
        when(mailboxBuilderPort.id(anyString())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.build()).thenReturn(mailboxPort);
        when(mailboxBuilderPort.reset()).thenReturn(mailboxBuilderPort);
        when(mailboxPort.getBuilder()).thenReturn(mailboxBuilderPort);
        when(setErrorEnumPort.singleton()).thenReturn(setErrorPort);

        doThrow(SetSingletonException.class).doNothing().when(mailboxRepository).save(any());

        CreatedResult<MailboxPort> result = standardCreateMailbox.create(setMailboxMethodCallPort);

        assertNotNull(result);
        assertTrue(result.created().isEmpty());
        assertTrue(result.notCreated().containsKey("key"));
        assertEquals(1, result.notCreated().size());
    }


}
