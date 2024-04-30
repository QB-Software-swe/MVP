package it.qbsoftware.business.services.set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetIdentityMethodCallServiceTest {
    
    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    IfInStateMatch ifInStateMatch;

    @Mock
    CreateIdentity createIdentity;

    @Mock
    UpdateIdentity updateIdentity;

    @Mock
    DestroyIdentity destroyIdentity;

    @Mock
    SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort;

    @Mock
    AccountState accountState;

    @Mock
    CreatedResult<IdentityPort> createdResult;

    @Mock
    UpdatedResult<IdentityPort> updatedResult;

    @Mock
    DestroyedResult destroyedResult;

    @Mock
    private SetIdentityMethodCallPort setIdentityMethodCallPort;

    @Mock
    private SetIdentityMethodResponsePort setIdentityMethodResponsePort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @InjectMocks
    private SetIdentityMethodCallService setIdentityMethodCallService;

    @Test
    public void testCall() throws AccountNotFoundException, StateMismatchException {
        String accountId = "accountId";
       
        when(setIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState, accountState);
        when(createIdentity.create(setIdentityMethodCallPort)).thenReturn(createdResult);
        when(updateIdentity.update(setIdentityMethodCallPort)).thenReturn(updatedResult);
        when(destroyIdentity.destroy(setIdentityMethodCallPort)).thenReturn(destroyedResult);
        when(setIdentityMethodResponseBuilderPort.reset()).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.oldState(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.newState(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.created(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.notCreated(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.updated(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.notUpdated(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.destroyed(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.notDestroyed(any())).thenReturn(setIdentityMethodResponseBuilderPort);
        when(setIdentityMethodResponseBuilderPort.build()).thenReturn(setIdentityMethodResponsePort);

        SetIdentityMethodResponsePort result = setIdentityMethodCallService.call(setIdentityMethodCallPort, previousResponses);

        verify(setIdentityMethodResponseBuilderPort).reset();
        verify(setIdentityMethodResponseBuilderPort).oldState(accountState.state());
        verify(setIdentityMethodResponseBuilderPort).newState(accountState.state());
        verify(setIdentityMethodResponseBuilderPort).created(createdResult.created());
        verify(setIdentityMethodResponseBuilderPort).notCreated(createdResult.notCreated());
        verify(setIdentityMethodResponseBuilderPort).updated(updatedResult.updated());
        verify(setIdentityMethodResponseBuilderPort).notUpdated(updatedResult.notUpdated());
        verify(setIdentityMethodResponseBuilderPort).destroyed(destroyedResult.destroyed());
        verify(setIdentityMethodResponseBuilderPort).notDestroyed(destroyedResult.notDestroyed());
        verify(setIdentityMethodResponseBuilderPort).build();

        assertEquals(setIdentityMethodResponsePort, result);
    }
}
