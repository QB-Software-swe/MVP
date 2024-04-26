package it.qbsoftware.business.services.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.ThreadChangesTracker;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesThreadMethodCallServiceTest {

    @Mock
    private ThreadChangesTrackerRepository threadChangesTrackerRepository;

    @Mock
    private ChangesThreadMethodResponseBuilderPort changesThreadMethodResponseBuilderPort;

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ChangesThreadMethodCallPort changesThreadMethodCallPort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private AccountState accountState;

    @Mock
    private ThreadChangesTracker threadChangesTracker;

    @Mock
    private ChangesThreadMethodResponsePort changesThreadMethodResponsePort;

    @InjectMocks
    private ChangesThreadMethodCallService changesThreadMethodCallService;


    @Test
    public void testValidCall() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 10L;

        when(changesThreadMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadChangesTrackerRepository.retrive(accountId)).thenReturn(threadChangesTracker);
        when(threadChangesTracker.created()).thenReturn(changesMap);
        when(threadChangesTracker.updated()).thenReturn(changesMap);
        when(threadChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesThreadMethodResponseBuilderPort.reset()).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.oldState(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.newState(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.created(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.updated(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.destroyed(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.build()).thenReturn(changesThreadMethodResponsePort);

        ChangesThreadMethodResponsePort result = changesThreadMethodCallService.call(changesThreadMethodCallPort, previousResponses);

        verify(threadChangesTrackerRepository).retrive(accountId);
        verify(accountStateRepository).retrive(accountId);
        verify(changesThreadMethodResponseBuilderPort).reset();
        verify(changesThreadMethodResponseBuilderPort).accountId(accountId);
        verify(changesThreadMethodResponseBuilderPort).oldState(any());
        verify(changesThreadMethodResponseBuilderPort).newState(accountState.threadState());
        verify(changesThreadMethodResponseBuilderPort).created(any());
        verify(changesThreadMethodResponseBuilderPort).updated(any());
        verify(changesThreadMethodResponseBuilderPort).destroyed(any());
        verify(changesThreadMethodResponseBuilderPort).hasMoreChanges(false);
        verify(changesThreadMethodResponseBuilderPort).build();
        assertEquals(changesThreadMethodResponsePort, result);
    }

    @Test
    public void testCallWithNegativeMaxChanges() {
        Long maxChanges = -10L;
        when(changesThreadMethodCallPort.getMaxChanges()).thenReturn(maxChanges);

        assertThrows(InvalidArgumentsException.class, () -> changesThreadMethodCallService.call(changesThreadMethodCallPort, null));
    }

    @Test
    public void testCallWithZeroMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 0L;
        
        when(changesThreadMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadChangesTrackerRepository.retrive(accountId)).thenReturn(threadChangesTracker);
        when(threadChangesTracker.created()).thenReturn(changesMap);
        when(threadChangesTracker.updated()).thenReturn(changesMap);
        when(threadChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesThreadMethodResponseBuilderPort.reset()).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.oldState(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.newState(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.created(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.updated(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.destroyed(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.build()).thenReturn(changesThreadMethodResponsePort);

        ChangesThreadMethodResponsePort result = changesThreadMethodCallService.call(changesThreadMethodCallPort, previousResponses);

        assertEquals(changesThreadMethodResponsePort, result);
    }

    @Test
    public void testCallWithNullMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();

        when(changesThreadMethodCallPort.getMaxChanges()).thenReturn(null);
        when(changesThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadChangesTrackerRepository.retrive(accountId)).thenReturn(threadChangesTracker);
        when(threadChangesTracker.created()).thenReturn(changesMap);
        when(threadChangesTracker.updated()).thenReturn(changesMap);
        when(threadChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesThreadMethodResponseBuilderPort.reset()).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.oldState(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.newState(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.created(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.updated(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.destroyed(any())).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesThreadMethodResponseBuilderPort);
        when(changesThreadMethodResponseBuilderPort.build()).thenReturn(changesThreadMethodResponsePort);

        ChangesThreadMethodResponsePort result = changesThreadMethodCallService.call(changesThreadMethodCallPort, previousResponses);

        assertEquals(changesThreadMethodResponsePort, result);
    }

    @Test
    public void testCallWithOneMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Map<String, String> changesMap2 = new HashMap<>();
        changesMap2.put("chiave1", "valore1b");
        changesMap2.put("chiave2", "valore2a");
        Long maxChanges = 1L;
        
        when(changesThreadMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadChangesTrackerRepository.retrive(accountId)).thenReturn(threadChangesTracker);
        when(threadChangesTracker.created()).thenReturn(changesMap);
        when(threadChangesTracker.updated()).thenReturn(changesMap2);
        when(threadChangesTracker.destroyed()).thenReturn(changesMap);

        assertThrows(CannotCalculateChangesException.class, () -> changesThreadMethodCallService.call(changesThreadMethodCallPort, null));
    }

}