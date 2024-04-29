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
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyEmail;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailMethodCallServiceTest {
    
    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    IfInStateMatch ifInStateMatch;

    @Mock
    CreateEmail createEmail;

    @Mock
    UpdateEmail updateEmail;

    @Mock
    DestroyEmail destroyEmail;

    @Mock
    SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;

    @Mock
    AccountState accountState;

    @Mock
    CreatedResult<EmailPort> createdResult;

    @Mock
    UpdatedResult<EmailPort> updatedResult;

    @Mock
    DestroyedResult destroyedResult;

    @Mock
    private SetEmailMethodCallPort setEmailMethodCallPort;

    @Mock
    private SetEmailMethodResponsePort setEmailMethodResponsePort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @InjectMocks
    private SetEmailMethodCallService setEmailMethodCallService;

    @Test
    public void testCall() throws AccountNotFoundException, StateMismatchException {
        String accountId = "accountId";
       
        when(setEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState, accountState);
        when(createEmail.create(setEmailMethodCallPort, previousResponses)).thenReturn(createdResult);
        when(updateEmail.update(setEmailMethodCallPort)).thenReturn(updatedResult);
        when(destroyEmail.destroy(setEmailMethodCallPort)).thenReturn(destroyedResult);
        when(setEmailMethodResponseBuilderPort.reset()).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.oldState(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.newState(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.created(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.notCreated(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.updated(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.notUpdated(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.destroyed(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.notDestroyed(any())).thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.build()).thenReturn(setEmailMethodResponsePort);

        SetEmailMethodResponsePort result = setEmailMethodCallService.call(setEmailMethodCallPort, previousResponses);

        verify(setEmailMethodResponseBuilderPort).reset();
        verify(setEmailMethodResponseBuilderPort).oldState(accountState.state());
        verify(setEmailMethodResponseBuilderPort).newState(accountState.state());
        verify(setEmailMethodResponseBuilderPort).created(createdResult.created());
        verify(setEmailMethodResponseBuilderPort).notCreated(createdResult.notCreated());
        verify(setEmailMethodResponseBuilderPort).updated(updatedResult.updated());
        verify(setEmailMethodResponseBuilderPort).notUpdated(updatedResult.notUpdated());
        verify(setEmailMethodResponseBuilderPort).destroyed(destroyedResult.destroyed());
        verify(setEmailMethodResponseBuilderPort).notDestroyed(destroyedResult.notDestroyed());
        verify(setEmailMethodResponseBuilderPort).build();

        assertEquals(setEmailMethodResponsePort, result);
    }
}
