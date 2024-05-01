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

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardCreateEmailTest {

    @Mock
    SetEmailMethodCallPort setEmailMethodCallPort;

    @Mock
    ListMultimapPort<String, ResponseInvocationPort> previousResponses;
    
    @Mock
    EmailBuilderPort emailBuilderPort;

    @Mock
    EmailRepository emailRepository;

    @Mock
    AccountStateRepository accountStateRepository;

    @Mock 
    EmailChangesTrackerRepository emailChangesTrackerRepository;

    @Mock
    MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    @Mock 
    ThreadChangesTrackerRepository threadChangesTrackerRepository;

    @Mock
    SetErrorEnumPort setErrorEnumPort;

    @Mock
    ThreadRepository threadRepository;

    @Mock
    EmailChangesTracker emailChangesTracker;

    @Mock
    ThreadChangesTracker threadChangesTracker;

    @Mock
    MailboxChangesTracker mailboxChangesTracker;

    @Mock
    EmailPort emailPort;

    @Mock
    ThreadPort threadPort;

    @Mock
    ThreadBuilderPort threadBuilderPort;

    @Mock
    AccountState accountState;

    @Mock
    CreationIdResolverPort creationIdResolverPort;
    
    @InjectMocks
    StandardCreateEmail standardCreateEmail;



    @Test
    public void testCreateWithNullMapEmailToCreate() throws AccountNotFoundException {     
        
        when(setEmailMethodCallPort.getCreate()).thenReturn(null);

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertTrue(result.created().isEmpty());
        assertTrue(result.notCreated().isEmpty());
    }

    @Test
    public void testCreateWithNotNullMapEmailToCreateAndNoExceptions() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(creationIdResolverPort.resolveIfNecessary(anyString(), any())).thenReturn("resolvedThreadId");

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");

        doNothing().when(emailRepository).save(any());
        doNothing().when(accountStateRepository).save(any());
        doNothing().when(threadRepository).save(any());

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());

    }

    @Test
    public void testCreateWithInvalidArgumentsException() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getMailboxIds()).thenReturn(null);

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertTrue(result.created().isEmpty());
        assertTrue(result.notCreated().containsKey("key"));
        assertEquals(1, result.notCreated().size());
    }

    @Test
    public void testCreateWithNullThreadId() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn(null);
        when(emailPort.getMailboxIds()).thenReturn(null);

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertTrue(result.created().isEmpty());
        assertTrue(result.notCreated().containsKey("key"));
        assertEquals(1, result.notCreated().size());
    }

    @Test
    public void testCreateWithSingletonException() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(creationIdResolverPort.resolveIfNecessary(anyString(), any())).thenReturn("resolvedThreadId");

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        
        doThrow(SetSingletonException.class).doNothing().when(emailRepository).save(any());
        doNothing().when(accountStateRepository).save(any());
        doNothing().when(threadRepository).save(any());

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());

    }

    @Test
    public void testCreateWithNotEmptyMap() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(creationIdResolverPort.resolveIfNecessary(anyString(), any())).thenReturn("resolvedThreadId");

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailPort.getMailboxIds()).thenReturn(Map.of("key",true));
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        when(mailboxChangesTrackerRepository.retrive(any())).thenReturn(mailboxChangesTracker);

        doNothing().when(emailRepository).save(any());
        doNothing().when(accountStateRepository).save(any());
        doNothing().when(threadRepository).save(any());

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());

    }

    @Test
    public void testCreateWithSameId() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn("resolvedThreadId");
        when(creationIdResolverPort.resolveIfNecessary(anyString(), any())).thenReturn("resolvedThreadId");

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");

        doNothing().when(emailRepository).save(any());
        doNothing().when(accountStateRepository).save(any());
        doNothing().when(threadRepository).save(any());

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());

    }

    @Test
    public void testCreateWithNullIdrResolver() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(creationIdResolverPort.resolveIfNecessary(anyString(), any())).thenReturn(null);

        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");

        doNothing().when(emailRepository).save(any());
        doNothing().when(accountStateRepository).save(any());
        doNothing().when(threadRepository).save(any());

        CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
        assertTrue(result.created().containsKey("key"));
        assertEquals(1, result.created().size());
        assertTrue(result.notCreated().isEmpty());

    }
}
