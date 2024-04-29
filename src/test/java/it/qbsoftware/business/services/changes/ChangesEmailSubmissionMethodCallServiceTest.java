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
import it.qbsoftware.business.domain.entity.changes.tracker.EmailSubmissionChangesTracker;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailSubmissionChangesTrackerRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailSubmissionMethodCallServiceTest {

    @Mock
    private EmailSubmissionChangesTrackerRepository emailSubmissionChangesTrackerRepository;

    @Mock
    private ChangesEmailSubmissionMethodResponseBuilderPort changesEmailSubmissionMethodResponseBuilderPort;

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ChangesEmailSubmissionMethodCallPort changesEmailSubmissionMethodCallPort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private AccountState accountState;

    @Mock
    private EmailSubmissionChangesTracker emailSubmissionChangesTracker;

    @Mock
    private ChangesEmailSubmissionMethodResponsePort changesEmailSubmissionMethodResponsePort;

    @InjectMocks
    private ChangesEmailSubmissionMethodCallService changesEmailSubmissionMethodCallService;


    @Test
    public void testValidCall() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 10L;

        when(changesEmailSubmissionMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailSubmissionChangesTrackerRepository.retrive(accountId)).thenReturn(emailSubmissionChangesTracker);
        when(emailSubmissionChangesTracker.created()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.updated()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesEmailSubmissionMethodResponseBuilderPort.reset()).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.oldState(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.newState(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.created(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.updated(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.destroyed(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.build()).thenReturn(changesEmailSubmissionMethodResponsePort);

        ChangesEmailSubmissionMethodResponsePort result = changesEmailSubmissionMethodCallService.call(changesEmailSubmissionMethodCallPort, previousResponses);

        verify(emailSubmissionChangesTrackerRepository).retrive(accountId);
        verify(accountStateRepository).retrive(accountId);
        verify(changesEmailSubmissionMethodResponseBuilderPort).reset();
        verify(changesEmailSubmissionMethodResponseBuilderPort).accountId(accountId);
        verify(changesEmailSubmissionMethodResponseBuilderPort).oldState(any());
        verify(changesEmailSubmissionMethodResponseBuilderPort).newState(accountState.state());
        verify(changesEmailSubmissionMethodResponseBuilderPort).created(any());
        verify(changesEmailSubmissionMethodResponseBuilderPort).updated(any());
        verify(changesEmailSubmissionMethodResponseBuilderPort).destroyed(any());
        verify(changesEmailSubmissionMethodResponseBuilderPort).hasMoreChanges(false);
        verify(changesEmailSubmissionMethodResponseBuilderPort).build();
        assertEquals(changesEmailSubmissionMethodResponsePort, result);
    }

    @Test
    public void testCallWithNegativeMaxChanges() {
        Long maxChanges = -10L;
        when(changesEmailSubmissionMethodCallPort.getMaxChanges()).thenReturn(maxChanges);

        assertThrows(InvalidArgumentsException.class, () -> changesEmailSubmissionMethodCallService.call(changesEmailSubmissionMethodCallPort, null));
    }

    @Test
    public void testCallWithZeroMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 0L;
        
        when(changesEmailSubmissionMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailSubmissionChangesTrackerRepository.retrive(accountId)).thenReturn(emailSubmissionChangesTracker);
        when(emailSubmissionChangesTracker.created()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.updated()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesEmailSubmissionMethodResponseBuilderPort.reset()).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.oldState(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.newState(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.created(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.updated(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.destroyed(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.build()).thenReturn(changesEmailSubmissionMethodResponsePort);

        ChangesEmailSubmissionMethodResponsePort result = changesEmailSubmissionMethodCallService.call(changesEmailSubmissionMethodCallPort, previousResponses);

        assertEquals(changesEmailSubmissionMethodResponsePort, result);
    }

    @Test
    public void testCallWithNullMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();

        when(changesEmailSubmissionMethodCallPort.getMaxChanges()).thenReturn(null);
        when(changesEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailSubmissionChangesTrackerRepository.retrive(accountId)).thenReturn(emailSubmissionChangesTracker);
        when(emailSubmissionChangesTracker.created()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.updated()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesEmailSubmissionMethodResponseBuilderPort.reset()).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.oldState(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.newState(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.created(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.updated(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.destroyed(any())).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesEmailSubmissionMethodResponseBuilderPort);
        when(changesEmailSubmissionMethodResponseBuilderPort.build()).thenReturn(changesEmailSubmissionMethodResponsePort);

        ChangesEmailSubmissionMethodResponsePort result = changesEmailSubmissionMethodCallService.call(changesEmailSubmissionMethodCallPort, previousResponses);

        assertEquals(changesEmailSubmissionMethodResponsePort, result);
    }

    @Test
    public void testCallWithOneMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Map<String, String> changesMap2 = new HashMap<>();
        changesMap2.put("chiave1", "valore1b");
        changesMap2.put("chiave2", "valore2a");
        Long maxChanges = 1L;
        
        when(changesEmailSubmissionMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailSubmissionChangesTrackerRepository.retrive(accountId)).thenReturn(emailSubmissionChangesTracker);
        when(emailSubmissionChangesTracker.created()).thenReturn(changesMap);
        when(emailSubmissionChangesTracker.updated()).thenReturn(changesMap2);
        when(emailSubmissionChangesTracker.destroyed()).thenReturn(changesMap);

        assertThrows(CannotCalculateChangesException.class, () -> changesEmailSubmissionMethodCallService.call(changesEmailSubmissionMethodCallPort, null));
    }

}