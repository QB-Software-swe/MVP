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
import it.qbsoftware.business.domain.entity.changes.tracker.IdentityChangesTracker;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesIdentityMethodCallServiceTest {

    @Mock
    private IdentityChangesTrackerRepository identityChangesTrackerRepository;

    @Mock
    private ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort;

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ChangesIdentityMethodCallPort changesIdentityMethodCallPort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private AccountState accountState;

    @Mock
    private IdentityChangesTracker identityChangesTracker;

    @Mock
    private ChangesIdentityMethodResponsePort changesIdentityMethodResponsePort;

    @InjectMocks
    private ChangesIdentityMethodCallService changesIdentityMethodCallService;


    @Test
    public void testValidCall() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 10L;

        when(changesIdentityMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(identityChangesTrackerRepository.retrive(accountId)).thenReturn(identityChangesTracker);
        when(identityChangesTracker.created()).thenReturn(changesMap);
        when(identityChangesTracker.updated()).thenReturn(changesMap);
        when(identityChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesIdentityMethodResponseBuilderPort.reset()).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.oldState(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.newState(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.created(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.updated(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.destroyed(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.build()).thenReturn(changesIdentityMethodResponsePort);

        ChangesIdentityMethodResponsePort result = changesIdentityMethodCallService.call(changesIdentityMethodCallPort, previousResponses);

        verify(identityChangesTrackerRepository).retrive(accountId);
        verify(accountStateRepository).retrive(accountId);
        verify(changesIdentityMethodResponseBuilderPort).reset();
        verify(changesIdentityMethodResponseBuilderPort).accountId(accountId);
        verify(changesIdentityMethodResponseBuilderPort).oldState(any());
        verify(changesIdentityMethodResponseBuilderPort).newState(accountState.identityState());
        verify(changesIdentityMethodResponseBuilderPort).created(any());
        verify(changesIdentityMethodResponseBuilderPort).updated(any());
        verify(changesIdentityMethodResponseBuilderPort).destroyed(any());
        verify(changesIdentityMethodResponseBuilderPort).hasMoreChanges(false);
        verify(changesIdentityMethodResponseBuilderPort).build();
        assertEquals(changesIdentityMethodResponsePort, result);
    }

    @Test
    public void testCallWithNegativeMaxChanges() {
        Long maxChanges = -10L;
        when(changesIdentityMethodCallPort.getMaxChanges()).thenReturn(maxChanges);

        assertThrows(InvalidArgumentsException.class, () -> changesIdentityMethodCallService.call(changesIdentityMethodCallPort, null));
    }

    @Test
    public void testCallWithZeroMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Long maxChanges = 0L;
        
        when(changesIdentityMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(identityChangesTrackerRepository.retrive(accountId)).thenReturn(identityChangesTracker);
        when(identityChangesTracker.created()).thenReturn(changesMap);
        when(identityChangesTracker.updated()).thenReturn(changesMap);
        when(identityChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesIdentityMethodResponseBuilderPort.reset()).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.oldState(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.newState(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.created(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.updated(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.destroyed(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.build()).thenReturn(changesIdentityMethodResponsePort);

        ChangesIdentityMethodResponsePort result = changesIdentityMethodCallService.call(changesIdentityMethodCallPort, previousResponses);

        assertEquals(changesIdentityMethodResponsePort, result);
    }

    @Test
    public void testCallWithNullMaxChanges() throws Exception {
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();

        when(changesIdentityMethodCallPort.getMaxChanges()).thenReturn(null);
        when(changesIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(identityChangesTrackerRepository.retrive(accountId)).thenReturn(identityChangesTracker);
        when(identityChangesTracker.created()).thenReturn(changesMap);
        when(identityChangesTracker.updated()).thenReturn(changesMap);
        when(identityChangesTracker.destroyed()).thenReturn(changesMap);
        when(changesIdentityMethodResponseBuilderPort.reset()).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.accountId(accountId)).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.oldState(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.newState(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.created(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.updated(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.destroyed(any())).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.hasMoreChanges(false)).thenReturn(changesIdentityMethodResponseBuilderPort);
        when(changesIdentityMethodResponseBuilderPort.build()).thenReturn(changesIdentityMethodResponsePort);

        ChangesIdentityMethodResponsePort result = changesIdentityMethodCallService.call(changesIdentityMethodCallPort, previousResponses);

        assertEquals(changesIdentityMethodResponsePort, result);
    }

    @Test
    public void testCallWithOneMaxChanges() throws Exception{
        String accountId = "testAccountId";
        Map<String, String> changesMap = new HashMap<>();
        Map<String, String> changesMap2 = new HashMap<>();
        changesMap2.put("chiave1", "valore1b");
        changesMap2.put("chiave2", "valore2a");
        Long maxChanges = 1L;
        
        when(changesIdentityMethodCallPort.getMaxChanges()).thenReturn(maxChanges);
        when(changesIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(identityChangesTrackerRepository.retrive(accountId)).thenReturn(identityChangesTracker);
        when(identityChangesTracker.created()).thenReturn(changesMap);
        when(identityChangesTracker.updated()).thenReturn(changesMap2);
        when(identityChangesTracker.destroyed()).thenReturn(changesMap);

        assertThrows(CannotCalculateChangesException.class, () -> changesIdentityMethodCallService.call(changesIdentityMethodCallPort, null));
    }

}