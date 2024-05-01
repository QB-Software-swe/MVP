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
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
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
    EmailBodyPartBuilderPort emailBodyPartBuilderPort;

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
        EmailBodyPartPort emailBodyPartPort = mock(EmailBodyPartPort.class);
        List<EmailBodyPartPort> attachments = new ArrayList<>();
        attachments.add(emailBodyPartPort);
        EmailBodyValuePort emailBodyValuePort = mock(EmailBodyValuePort.class);
        Map<String, EmailBodyValuePort> bodyValue = new HashMap<>();
        bodyValue.put("partId", emailBodyValuePort);

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailPort.getAttachments()).thenReturn(attachments);
        when(emailPort.getBodyValues()).thenReturn(bodyValue);
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
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);
        when(emailBodyPartPort.getPartId()).thenReturn("partId");
        when(emailBodyPartPort.toBuilder()).thenReturn(emailBodyPartBuilderPort);
        when(emailBodyPartBuilderPort.reset()).thenReturn(emailBodyPartBuilderPort);
        when(emailBodyPartBuilderPort.blobId(any())).thenReturn(emailBodyPartBuilderPort);
        when(emailBodyPartBuilderPort.charset(any())).thenReturn(emailBodyPartBuilderPort);
        when(emailBodyPartBuilderPort.name(any())).thenReturn(emailBodyPartBuilderPort);
        when(emailBodyPartBuilderPort.size(any())).thenReturn(emailBodyPartBuilderPort);
        when(emailBodyPartBuilderPort.build()).thenReturn(emailBodyPartPort);

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
    public void testCreateWithNullAttachments() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();
        EmailBodyPartPort emailBodyPartPort = mock(EmailBodyPartPort.class);
        List<EmailBodyPartPort> attachments = new ArrayList<>();
        attachments.add(emailBodyPartPort);
        EmailBodyValuePort emailBodyValuePort = mock(EmailBodyValuePort.class);
        Map<String, EmailBodyValuePort> bodyValue = new HashMap<>();
        bodyValue.put("partId", emailBodyValuePort);

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailPort.getAttachments()).thenReturn(null);
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
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);

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
    public void testCreateWithNullEmailIds() throws Exception {     
        UUID uuid = UUID.randomUUID();
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();
        emailIds.add("accountId/"+uuid.toString());
        EmailBodyPartPort emailBodyPartPort = mock(EmailBodyPartPort.class);
        List<EmailBodyPartPort> attachments = new ArrayList<>();
        attachments.add(emailBodyPartPort);
        EmailBodyValuePort emailBodyValuePort = mock(EmailBodyValuePort.class);
        Map<String, EmailBodyValuePort> bodyValue = new HashMap<>();
        bodyValue.put("partId", emailBodyValuePort);

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailPort.getAttachments()).thenReturn(null);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);

        doNothing().when(emailRepository).save(any());
        doNothing().when(accountStateRepository).save(any());

        try(MockedStatic<UUID> UUIDStatic = Mockito.mockStatic(UUID.class)){
            UUIDStatic.when(UUID::randomUUID).thenReturn(uuid);
            CreatedResult<EmailPort> result = standardCreateEmail.create(setEmailMethodCallPort, previousResponses);

            assertNotNull(result);
            assertTrue(result.created().containsKey("key"));
            assertEquals(1, result.created().size());
            assertTrue(result.notCreated().isEmpty());
        }
    }

    @Test
    public void testCreateWithNotNullMapEmailToCreateAndNoExceptionsAndNotNullValue() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();
        EmailBodyPartPort emailBodyPartPort = mock(EmailBodyPartPort.class);
        List<EmailBodyPartPort> attachments = new ArrayList<>();
        attachments.add(emailBodyPartPort);

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailPort.getAttachments()).thenReturn(attachments);
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
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);
        when(emailBodyPartPort.getPartId()).thenReturn(null);

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
        when(emailPort.getMailboxIds()).thenReturn(null);
        when(emailPort.getSubject()).thenReturn("subject");

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
        when(emailPort.getMailboxIds()).thenReturn(null);
        when(emailPort.getSubject()).thenReturn("subject");

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
        SetSingletonException singletonException = new SetSingletonException();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
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
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);
        
        doThrow(singletonException).doNothing().when(emailRepository).save(any());
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
        when(creationIdResolverPort.resolveIfNecessary(anyString(), any())).thenReturn("resolvedThreadId");
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
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
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);

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
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(threadRepository.retriveOne(any())).thenReturn(threadPort);
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailChangesTrackerRepository.retrive(any())).thenReturn(emailChangesTracker);
        when(threadChangesTrackerRepository.retrive(any())).thenReturn(threadChangesTracker);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);

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
    public void testCreateWithNullIdResolver() throws Exception {     
        EmailPort emailPort = mock(EmailPort.class);
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();

        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getSubject()).thenReturn("subject");
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
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
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);

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
    public void testCreateWithSubjectRe() throws Exception {     
        Map<String, EmailPort> mapEmailToCreate = new HashMap<>();
        mapEmailToCreate.put("key", emailPort);
        List<String> emailIds = new ArrayList<>();
        String accountId = "accountId";
        EmailPort emailPort2 = mock(EmailPort.class);
        EmailPort[] emails = new EmailPort[] {emailPort2};


        when(setEmailMethodCallPort.getCreate()).thenReturn(mapEmailToCreate);
        when(emailPort.getSubject()).thenReturn("Re:qualcosa");
        when(emailPort2.getSubject()).thenReturn("qualcosa");
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.id(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.blobId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.threadId(anyString())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.size(anyLong())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.mailboxIds(anyMap())).thenReturn(emailBuilderPort);
        when(emailBuilderPort.receivedAt(any())).thenReturn(emailBuilderPort);
        when(setEmailMethodCallPort.accountId()).thenReturn(accountId);
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
        when(emailRepository.retriveAll(any())).thenReturn(new RetrivedEntity<>(emails, new String[] {}));
        when(emailPort2.getThreadId()).thenReturn("threadId");
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);
        when(threadBuilderPort.clearEmailIds()).thenReturn(threadBuilderPort);
        when(emailBuilderPort.clearMailboxIds()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.hasAttachment(any())).thenReturn(emailBuilderPort);
        
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
