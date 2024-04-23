package it.qbsoftware.business.services.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.business.domain.entity.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.response.AccountNotFoundMethodErrorResponse;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetThreadMethodCallServiceTest {

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private ThreadRepository threadRepository;

    @Mock
    private GetThreadMethodCallPort getThreadMethodCallPort;

    @Mock
    private GetReferenceIdsResolver getReferenceIdsResolver;

    @Mock
    private ThreadPropertiesFilter threadPropertiesFilter;

    @Mock
    private InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;

    @Mock
    private InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;

    @Mock
    private GetThreadMethodResponsePort getThreadMethodResponsePort;

    @Mock
    private GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort;

    @InjectMocks
    private GetThreadMethodCallService getThreadMethodCallService;


    @Test
    public void testCallWithRetrive() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {

        String accountId = "testAccountId";
        String[] threadIds = new String[] { "threadId1", "threadId2" };
        AccountState accountState = new AccountState(accountId);
        ThreadPort[] threads = new ThreadPort[] { new ThreadAdapter(null), new ThreadAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(threadIds);
        when(threadRepository.retrive(any())).thenReturn(new RetrivedEntity<>(threads, new String[] {}));
        when(getThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadPropertiesFilter.filter(any(), any())).thenReturn(threads);
        when(getThreadMethodResponseBuilderPort.reset()).thenReturn(getThreadMethodResponseBuilderPort);
        
        when(getThreadMethodResponseBuilderPort.list(any())).thenReturn(getThreadMethodResponseBuilderPort);
        
        when(getThreadMethodResponseBuilderPort.notFound(any())).thenReturn(getThreadMethodResponseBuilderPort);
        
        when(getThreadMethodResponseBuilderPort.state(any())).thenReturn(getThreadMethodResponseBuilderPort);
        when(getThreadMethodResponseBuilderPort.build()).thenReturn(getThreadMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);

        verify(getThreadMethodResponseBuilderPort).reset();
        verify(getThreadMethodResponseBuilderPort).list(threads);
        verify(getThreadMethodResponseBuilderPort).notFound(any());
        verify(getThreadMethodResponseBuilderPort).state(accountState.emailState());
        verify(getThreadMethodResponseBuilderPort).build();
        verify(threadRepository).retrive(threadIds);
        assertEquals(methodResponsePorts[0], getThreadMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);
    }


    @Test
    public void testCallWithAccountNotFoundException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        String accountId = "testAccountId";

        when(accountStateRepository.retrive(accountId)).thenThrow(new AccountNotFoundException());
        when(getThreadMethodCallPort.accountId()).thenReturn(accountId);
        
        MethodResponsePort[] methodResponsePorts = getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof AccountNotFoundMethodErrorResponse);
    }

    @Test
    public void testCallWithInvalidResultReferenceException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        when(getReferenceIdsResolver.resolve(any(), any())).thenThrow(new InvalidResultReferenceExecption());
        
        MethodResponsePort[] methodResponsePorts = getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidResultReferenceMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidArgumentsException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
       
        String[] threadIds = new String[] { "threadId1", "threadId2" };
        ThreadPort[] threads = new ThreadPort[] { new ThreadAdapter(null), new ThreadAdapter(null) };
        
        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(threadIds);
        when(threadPropertiesFilter.filter(any(), any())).thenThrow(new InvalidArgumentsException());
        when(threadRepository.retrive(any())).thenReturn(new RetrivedEntity<>(threads, new String[] {}));
        
        MethodResponsePort[] methodResponsePorts = getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidArgumentsMethodErrorResponsePort);
    }



    @Test
    public void testCallWithRetriveAll() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException{
        String accountId = "testAccountId";
        String[] threadIds = null;
        AccountState accountState = new AccountState(accountId);
        ThreadPort[] threads = new ThreadPort[] { new ThreadAdapter(null), new ThreadAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(threadIds);
        when(threadRepository.retriveAll(any())).thenReturn(new RetrivedEntity<>(threads, new String[] {}));
        when(getThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadPropertiesFilter.filter(any(), any())).thenReturn(threads);
        when(getThreadMethodResponseBuilderPort.reset()).thenReturn(getThreadMethodResponseBuilderPort);
        
        when(getThreadMethodResponseBuilderPort.list(any())).thenReturn(getThreadMethodResponseBuilderPort);
        
        when(getThreadMethodResponseBuilderPort.notFound(any())).thenReturn(getThreadMethodResponseBuilderPort);
        
        when(getThreadMethodResponseBuilderPort.state(any())).thenReturn(getThreadMethodResponseBuilderPort);
        when(getThreadMethodResponseBuilderPort.build()).thenReturn(getThreadMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);

        verify(getThreadMethodResponseBuilderPort).reset();
        verify(getThreadMethodResponseBuilderPort).list(threads);
        verify(getThreadMethodResponseBuilderPort).notFound(any());
        verify(getThreadMethodResponseBuilderPort).state(accountState.emailState());
        verify(getThreadMethodResponseBuilderPort).build();
        verify(threadRepository).retriveAll(accountId);
        assertEquals(methodResponsePorts[0], getThreadMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);

    }


}