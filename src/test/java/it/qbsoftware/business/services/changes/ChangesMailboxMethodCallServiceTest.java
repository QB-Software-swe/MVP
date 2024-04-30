package it.qbsoftware.business.services.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesMailboxMethodCallServiceTest {

    @Mock
    private MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    @Mock
    private ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort;

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ChangesMailboxMethodCallPort changesMailboxMethodCallPort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private AccountState accountState;

    @Mock
    private MailboxChangesTracker mailboxChangesTracker;

    @Mock
    private ChangesMailboxMethodResponsePort changesMailboxMethodResponsePort;

    @InjectMocks
    private ChangesMailboxMethodCallService changesMailboxMethodCallService;


    @Test
    public void testValidCall() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 10L;

        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(mailboxChangesTrackerRepository.retrive(accountId)).thenReturn(mailboxChangesTracker);
        when(mailboxChangesTracker.created()).thenReturn(changesMap);
        when(mailboxChangesTracker.updated()).thenReturn(changesMap);
        when(mailboxChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesMailboxMethodResponseBuilderPort.reset()).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.oldState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.newState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.created(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.updated(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.destroyed(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.build()).thenReturn(changesMailboxMethodResponsePort);

        ChangesMailboxMethodResponsePort result = changesMailboxMethodCallService.call(changesMailboxMethodCallPort, previousResponses);

        verify(mailboxChangesTrackerRepository).retrive(accountId);
        verify(accountStateRepository).retrive(accountId);
        verify(changesMailboxMethodResponseBuilderPort).reset();
        verify(changesMailboxMethodResponseBuilderPort).accountId(accountId);
        verify(changesMailboxMethodResponseBuilderPort).oldState(any());
        verify(changesMailboxMethodResponseBuilderPort).newState(accountState.state());
        verify(changesMailboxMethodResponseBuilderPort).created(any());
        verify(changesMailboxMethodResponseBuilderPort).updated(any());
        verify(changesMailboxMethodResponseBuilderPort).destroyed(any());
        verify(changesMailboxMethodResponseBuilderPort).hasMoreChanges(false);
        verify(changesMailboxMethodResponseBuilderPort).build();
        assertEquals(changesMailboxMethodResponsePort, result);
    }

    @Test
    public void testCallWithNegativeMaxChanges() {
        Long maxChanges = -10L;
        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(maxChanges);

        assertThrows(InvalidArgumentsException.class, () -> changesMailboxMethodCallService.call(changesMailboxMethodCallPort, null));
    }

    @Test
    public void testCallWithZeroMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 0L;
        
        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(mailboxChangesTrackerRepository.retrive(accountId)).thenReturn(mailboxChangesTracker);
        when(mailboxChangesTracker.created()).thenReturn(changesMap);
        when(mailboxChangesTracker.updated()).thenReturn(changesMap);
        when(mailboxChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesMailboxMethodResponseBuilderPort.reset()).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.oldState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.newState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.created(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.updated(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.destroyed(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.build()).thenReturn(changesMailboxMethodResponsePort);

        ChangesMailboxMethodResponsePort result = changesMailboxMethodCallService.call(changesMailboxMethodCallPort, previousResponses);

        assertEquals(changesMailboxMethodResponsePort, result);
    }

    @Test
    public void testCallWithNullMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();

        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(null);
        when(changesMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(mailboxChangesTrackerRepository.retrive(accountId)).thenReturn(mailboxChangesTracker);
        when(mailboxChangesTracker.created()).thenReturn(changesMap);
        when(mailboxChangesTracker.updated()).thenReturn(changesMap);
        when(mailboxChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesMailboxMethodResponseBuilderPort.reset()).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.oldState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.newState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.created(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.updated(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.destroyed(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.build()).thenReturn(changesMailboxMethodResponsePort);

        ChangesMailboxMethodResponsePort result = changesMailboxMethodCallService.call(changesMailboxMethodCallPort, previousResponses);

        assertEquals(changesMailboxMethodResponsePort, result);
    }

    @Test
    public void testCallWithOneMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Map<String, String> changesMap2 = new HashMap<>();
        changesMap2.put("chiave1", "valore1b");
        changesMap2.put("chiave2", "valore2a");
        Long maxChanges = 1L;
        
        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(mailboxChangesTrackerRepository.retrive(accountId)).thenReturn(mailboxChangesTracker);
        when(mailboxChangesTracker.created()).thenReturn(changesMap);
        when(mailboxChangesTracker.updated()).thenReturn(changesMap2);
        when(mailboxChangesTracker.destroyed()).thenReturn(changesMap);

        assertThrows(CannotCalculateChangesException.class, () -> changesMailboxMethodCallService.call(changesMailboxMethodCallPort, null));
    }

    @Test
    public void testCallWithValidSinceState() throws Exception {
        String accountId = "testAccountId";
        String sinceState = "sinceState";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 10L;

        when(changesMailboxMethodCallPort.getSinceState()).thenReturn(sinceState);
        when(accountState.state()).thenReturn(sinceState);
        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(changesMailboxMethodResponseBuilderPort.reset()).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.oldState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.newState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.created(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.updated(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.destroyed(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.updatedProperties(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.build()).thenReturn(changesMailboxMethodResponsePort);

        ChangesMailboxMethodResponsePort result = changesMailboxMethodCallService.call(changesMailboxMethodCallPort, previousResponses);

        assertNotNull(result);
    }

    @Test
    public void testCallWithDifferentState() throws Exception {
        String accountId = "testAccountId";
        String sinceState = "sinceState";
        Long maxChanges = 10L;
        Map<String, String> changesMap = new HashMap<>();

        when(changesMailboxMethodCallPort.getSinceState()).thenReturn(sinceState);
        when(changesMailboxMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountState.state()).thenReturn("notSinceState");
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(changesMailboxMethodResponseBuilderPort.reset()).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.oldState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.newState(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.created(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.updated(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.destroyed(any())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.hasMoreChanges(anyBoolean())).thenReturn(changesMailboxMethodResponseBuilderPort);
        when(changesMailboxMethodResponseBuilderPort.build()).thenReturn(mock(ChangesMailboxMethodResponsePort.class));
        when(mailboxChangesTrackerRepository.retrive(accountId)).thenReturn(mailboxChangesTracker);
        when(mailboxChangesTracker.created()).thenReturn(changesMap);
        when(mailboxChangesTracker.updated()).thenReturn(changesMap);
        when(mailboxChangesTracker.destroyed()).thenReturn(changesMap);

        ChangesMailboxMethodResponsePort result = changesMailboxMethodCallService.call(changesMailboxMethodCallPort, null);

        assertNotNull(result);
    }

}