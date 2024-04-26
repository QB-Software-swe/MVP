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

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetMailboxMethodCallServiceTest {

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private MailboxRepository mailboxRepository;

    @Mock
    private GetMailboxMethodCallPort getMailboxMethodCallPort;

    @Mock
    private GetReferenceIdsResolver getReferenceIdsResolver;

    @Mock
    private MailboxPropertiesFilter mailboxPropertiesFilter;

    @Mock
    private AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    @Mock
    private InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;

    @Mock
    private InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;

    @Mock
    private GetMailboxMethodResponsePort getMailboxMethodResponsePort;

    @Mock
    private GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort;

    @InjectMocks
    private GetMailboxMethodCallService getMailboxMethodCallService;


    @Test
    public void testCallWithRetrive() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {

        String accountId = "testAccountId";
        String[] mailboxIds = new String[] { "mailboxId1", "mailboxId2" };
        AccountState accountState = new AccountState(accountId);
        MailboxPort[] mailboxs = new MailboxPort[] { new MailboxAdapter(null), new MailboxAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(mailboxIds);
        when(mailboxRepository.retrive(any())).thenReturn(new RetrivedEntity<>(mailboxs, new String[] {}));
        when(getMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(mailboxPropertiesFilter.filter(any(), any())).thenReturn(mailboxs);
        when(getMailboxMethodResponseBuilderPort.reset()).thenReturn(getMailboxMethodResponseBuilderPort);
        
        when(getMailboxMethodResponseBuilderPort.list(any())).thenReturn(getMailboxMethodResponseBuilderPort);
        
        when(getMailboxMethodResponseBuilderPort.notFound(any())).thenReturn(getMailboxMethodResponseBuilderPort);
        
        when(getMailboxMethodResponseBuilderPort.state(any())).thenReturn(getMailboxMethodResponseBuilderPort);
        when(getMailboxMethodResponseBuilderPort.build()).thenReturn(getMailboxMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);

        verify(getMailboxMethodResponseBuilderPort).reset();
        verify(getMailboxMethodResponseBuilderPort).list(mailboxs);
        verify(getMailboxMethodResponseBuilderPort).notFound(any());
        verify(getMailboxMethodResponseBuilderPort).state(accountState.mailboxState());
        verify(getMailboxMethodResponseBuilderPort).build();
        verify(mailboxRepository).retrive(mailboxIds);
        assertEquals(methodResponsePorts[0], getMailboxMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);
    }


    @Test
    public void testCallWithAccountNotFoundException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        String accountId = "testAccountId";

        when(accountStateRepository.retrive(accountId)).thenThrow(new AccountNotFoundException());
        when(getMailboxMethodCallPort.accountId()).thenReturn(accountId);
        
        MethodResponsePort[] methodResponsePorts = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof AccountNotFoundMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidResultReferenceException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        when(getReferenceIdsResolver.resolve(any(), any())).thenThrow(new InvalidResultReferenceExecption());
        
        MethodResponsePort[] methodResponsePorts = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidResultReferenceMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidArgumentsException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
       
        String[] mailboxIds = new String[] { "mailboxId1", "mailboxId2" };
        MailboxPort[] mailboxs = new MailboxPort[] { new MailboxAdapter(null), new MailboxAdapter(null) };
        
        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(mailboxIds);
        when(mailboxPropertiesFilter.filter(any(), any())).thenThrow(new InvalidArgumentsException());
        when(mailboxRepository.retrive(any())).thenReturn(new RetrivedEntity<>(mailboxs, new String[] {}));
        
        MethodResponsePort[] methodResponsePorts = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidArgumentsMethodErrorResponsePort);
    }



    @Test
    public void testCallWithRetriveAll() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException{
        String accountId = "testAccountId";
        String[] mailboxIds = null;
        AccountState accountState = new AccountState(accountId);
        MailboxPort[] mailboxs = new MailboxPort[] { new MailboxAdapter(null), new MailboxAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(mailboxIds);
        when(mailboxRepository.retriveAll(any())).thenReturn(new RetrivedEntity<>(mailboxs, new String[] {}));
        when(getMailboxMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(mailboxPropertiesFilter.filter(any(), any())).thenReturn(mailboxs);
        when(getMailboxMethodResponseBuilderPort.reset()).thenReturn(getMailboxMethodResponseBuilderPort);
        
        when(getMailboxMethodResponseBuilderPort.list(any())).thenReturn(getMailboxMethodResponseBuilderPort);
        
        when(getMailboxMethodResponseBuilderPort.notFound(any())).thenReturn(getMailboxMethodResponseBuilderPort);
        
        when(getMailboxMethodResponseBuilderPort.state(any())).thenReturn(getMailboxMethodResponseBuilderPort);
        when(getMailboxMethodResponseBuilderPort.build()).thenReturn(getMailboxMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);

        verify(getMailboxMethodResponseBuilderPort).reset();
        verify(getMailboxMethodResponseBuilderPort).list(mailboxs);
        verify(getMailboxMethodResponseBuilderPort).notFound(any());
        verify(getMailboxMethodResponseBuilderPort).state(accountState.mailboxState());
        verify(getMailboxMethodResponseBuilderPort).build();
        verify(mailboxRepository).retriveAll(accountId);
        assertEquals(methodResponsePorts[0], getMailboxMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);

    }


}