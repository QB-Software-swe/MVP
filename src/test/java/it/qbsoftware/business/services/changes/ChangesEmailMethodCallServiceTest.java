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

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailMethodCallServiceTest {

    @Mock private EmailChangesTrackerRepository emailChangesTrackerRepository;

    @Mock private ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort;

    @Mock private AccountStateRepository accountStateRepository;

    @Mock private ChangesEmailMethodCallPort changesEmailMethodCallPort;

    @Mock private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock private AccountState accountState;

    @Mock private EmailChangesTracker emailChangesTracker;

    @Mock private ChangesEmailMethodResponsePort changesEmailMethodResponsePort;

    @InjectMocks private ChangesEmailMethodCallService changesEmailMethodCallService;

    @Test
    public void testValidCall() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 10L;

        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailChangesTrackerRepository.retrive(accountId)).thenReturn(emailChangesTracker);
        when(emailChangesTracker.created()).thenReturn(changesMap);
        when(emailChangesTracker.updated()).thenReturn(changesMap);
        when(emailChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesEmailMethodResponseBuilderPort.reset())
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.accountId(accountId))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.oldState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.newState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.created(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.updated(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.destroyed(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.hasMoreChanges(false))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.build())
                .thenReturn(changesEmailMethodResponsePort);

        ChangesEmailMethodResponsePort result =
                changesEmailMethodCallService.call(changesEmailMethodCallPort, previousResponses);

        verify(emailChangesTrackerRepository).retrive(accountId);
        verify(accountStateRepository).retrive(accountId);
        verify(changesEmailMethodResponseBuilderPort).reset();
        verify(changesEmailMethodResponseBuilderPort).accountId(accountId);
        verify(changesEmailMethodResponseBuilderPort).oldState(any());
        verify(changesEmailMethodResponseBuilderPort).newState(accountState.state());
        verify(changesEmailMethodResponseBuilderPort).created(any());
        verify(changesEmailMethodResponseBuilderPort).updated(any());
        verify(changesEmailMethodResponseBuilderPort).destroyed(any());
        verify(changesEmailMethodResponseBuilderPort).hasMoreChanges(false);
        verify(changesEmailMethodResponseBuilderPort).build();
        assertEquals(changesEmailMethodResponsePort, result);
    }

    @Test
    public void testCallWithNegativeMaxChanges() {
        Long maxChanges = -10L;
        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(maxChanges);

        assertThrows(
                InvalidArgumentsException.class,
                () -> changesEmailMethodCallService.call(changesEmailMethodCallPort, null));
    }

    @Test
    public void testCallWithZeroMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 0L;

        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailChangesTrackerRepository.retrive(accountId)).thenReturn(emailChangesTracker);
        when(emailChangesTracker.created()).thenReturn(changesMap);
        when(emailChangesTracker.updated()).thenReturn(changesMap);
        when(emailChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesEmailMethodResponseBuilderPort.reset())
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.accountId(accountId))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.oldState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.newState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.created(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.updated(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.destroyed(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.hasMoreChanges(false))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.build())
                .thenReturn(changesEmailMethodResponsePort);

        ChangesEmailMethodResponsePort result =
                changesEmailMethodCallService.call(changesEmailMethodCallPort, previousResponses);

        assertEquals(changesEmailMethodResponsePort, result);
    }

    @Test
    public void testCallWithNullMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();

        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(null);
        when(changesEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailChangesTrackerRepository.retrive(accountId)).thenReturn(emailChangesTracker);
        when(emailChangesTracker.created()).thenReturn(changesMap);
        when(emailChangesTracker.updated()).thenReturn(changesMap);
        when(emailChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesEmailMethodResponseBuilderPort.reset())
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.accountId(accountId))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.oldState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.newState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.created(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.updated(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.destroyed(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.hasMoreChanges(false))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.build())
                .thenReturn(changesEmailMethodResponsePort);

        ChangesEmailMethodResponsePort result =
                changesEmailMethodCallService.call(changesEmailMethodCallPort, previousResponses);

        assertEquals(changesEmailMethodResponsePort, result);
    }

    @Test
    public void testCallWithOneMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Map<String, String> changesMap2 = new HashMap<>();
        changesMap2.put("chiave1", "valore1b");
        changesMap2.put("chiave2", "valore2a");
        Long maxChanges = 1L;

        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailChangesTrackerRepository.retrive(accountId)).thenReturn(emailChangesTracker);
        when(emailChangesTracker.created()).thenReturn(changesMap);
        when(emailChangesTracker.updated()).thenReturn(changesMap2);
        when(emailChangesTracker.destroyed()).thenReturn(changesMap);

        assertThrows(
                CannotCalculateChangesException.class,
                () -> changesEmailMethodCallService.call(changesEmailMethodCallPort, null));
    }

    @Test
    public void testCallWithValidSinceState() throws Exception {
        String accountId = "testAccountId";
        String sinceState = "sinceState";
        Long maxChanges = 10L;

        when(changesEmailMethodCallPort.getSinceState()).thenReturn(sinceState);
        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountState.state()).thenReturn(sinceState);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(changesEmailMethodResponseBuilderPort.reset())
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.accountId(accountId))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.oldState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.newState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.created(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.updated(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.destroyed(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.hasMoreChanges(anyBoolean()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.build())
                .thenReturn(mock(ChangesEmailMethodResponsePort.class));

        ChangesEmailMethodResponsePort result =
                changesEmailMethodCallService.call(changesEmailMethodCallPort, null);

        assertNotNull(result);
    }

    @Test
    public void testCallWithDifferentState() throws Exception {
        String accountId = "testAccountId";
        String sinceState = "sinceState";
        Long maxChanges = 10L;
        Map<String, String> changesMap = new HashMap<>();

        when(changesEmailMethodCallPort.getSinceState()).thenReturn(sinceState);
        when(changesEmailMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountState.state()).thenReturn("notSinceState");
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(changesEmailMethodResponseBuilderPort.reset())
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.accountId(accountId))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.oldState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.newState(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.created(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.updated(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.destroyed(any()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.hasMoreChanges(anyBoolean()))
                .thenReturn(changesEmailMethodResponseBuilderPort);
        when(changesEmailMethodResponseBuilderPort.build())
                .thenReturn(mock(ChangesEmailMethodResponsePort.class));
        when(emailChangesTrackerRepository.retrive(accountId)).thenReturn(emailChangesTracker);
        when(emailChangesTracker.created()).thenReturn(changesMap);
        when(emailChangesTracker.updated()).thenReturn(changesMap);
        when(emailChangesTracker.destroyed()).thenReturn(changesMap);

        ChangesEmailMethodResponsePort result =
                changesEmailMethodCallService.call(changesEmailMethodCallPort, null);

        assertNotNull(result);
    }
}
