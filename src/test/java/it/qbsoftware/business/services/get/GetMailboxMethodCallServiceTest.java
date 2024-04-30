package it.qbsoftware.business.services.get;

import static org.junit.Assert.assertEquals;
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
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
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

        GetMailboxMethodResponsePort result = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);

        verify(getMailboxMethodResponseBuilderPort).reset();
        verify(getMailboxMethodResponseBuilderPort).list(mailboxs);
        verify(getMailboxMethodResponseBuilderPort).notFound(any());
        verify(getMailboxMethodResponseBuilderPort).state(accountState.state());
        verify(getMailboxMethodResponseBuilderPort).build();
        verify(mailboxRepository).retrive(mailboxIds);
        assertEquals(result, getMailboxMethodResponsePort);
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

        GetMailboxMethodResponsePort result = getMailboxMethodCallService.call(getMailboxMethodCallPort, previousResponses);

        verify(getMailboxMethodResponseBuilderPort).reset();
        verify(getMailboxMethodResponseBuilderPort).list(mailboxs);
        verify(getMailboxMethodResponseBuilderPort).notFound(any());
        verify(getMailboxMethodResponseBuilderPort).state(accountState.state());
        verify(getMailboxMethodResponseBuilderPort).build();
        verify(mailboxRepository).retriveAll(accountId);
        assertEquals(result, getMailboxMethodResponsePort);

    }


}