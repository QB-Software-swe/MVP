package it.qbsoftware.business.services.set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreatedResult;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyedResult;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetMailboxMethodCallServiceTest {

    @Mock private AccountStateRepository accountStateRepository;

    @Mock IfInStateMatch ifInStateMatch;

    @Mock CreateMailbox createMailbox;

    @Mock UpdateMailbox updateMailbox;

    @Mock DestroyMailbox destroyMailbox;

    @Mock SetMailboxMethodResponseBuilderPort setMailboxMethodResponseBuilderPort;

    @Mock AccountState accountState;

    @Mock CreatedResult<MailboxPort> createdResult;

    @Mock UpdatedResult<MailboxPort> updatedResult;

    @Mock DestroyedResult destroyedResult;

    @Mock private SetMailboxMethodCallPort setMailboxMethodCallPort;

    @Mock private SetMailboxMethodResponsePort setMailboxMethodResponsePort;

    @Mock private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @InjectMocks private SetMailboxMethodCallService setMailboxMethodCallService;

    @Test
    public void testCall() throws AccountNotFoundException, StateMismatchException {
        String accountId = "accountId";

        when(setMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState, accountState);
        when(createMailbox.create(setMailboxMethodCallPort)).thenReturn(createdResult);
        when(updateMailbox.update(setMailboxMethodCallPort, previousResponses))
                .thenReturn(updatedResult);
        when(destroyMailbox.destroy(setMailboxMethodCallPort)).thenReturn(destroyedResult);
        when(setMailboxMethodResponseBuilderPort.reset())
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.oldState(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.newState(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.created(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.notCreated(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.updated(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.notUpdated(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.destroyed(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.notDestroyed(any()))
                .thenReturn(setMailboxMethodResponseBuilderPort);
        when(setMailboxMethodResponseBuilderPort.build()).thenReturn(setMailboxMethodResponsePort);

        SetMailboxMethodResponsePort result =
                setMailboxMethodCallService.call(setMailboxMethodCallPort, previousResponses);

        verify(setMailboxMethodResponseBuilderPort).reset();
        verify(setMailboxMethodResponseBuilderPort).oldState(accountState.state());
        verify(setMailboxMethodResponseBuilderPort).newState(accountState.state());
        verify(setMailboxMethodResponseBuilderPort).created(createdResult.created());
        verify(setMailboxMethodResponseBuilderPort).notCreated(createdResult.notCreated());
        verify(setMailboxMethodResponseBuilderPort).updated(updatedResult.updated());
        verify(setMailboxMethodResponseBuilderPort).notUpdated(updatedResult.notUpdated());
        verify(setMailboxMethodResponseBuilderPort).destroyed(destroyedResult.destroyed());
        verify(setMailboxMethodResponseBuilderPort).notDestroyed(destroyedResult.notDestroyed());
        verify(setMailboxMethodResponseBuilderPort).build();

        assertEquals(setMailboxMethodResponsePort, result);
    }
}
