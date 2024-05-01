package it.qbsoftware.business.services.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.EmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetEmailMethodCallServiceTest {

    @Mock
    private GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort;

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private GetReferenceIdsResolver getReferenceIdsResolver;

    @Mock
    private EmailPropertiesFilter emailPropertiesFilter;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private GetEmailMethodCallPort getEmailMethodCallPort;

    @Mock
    private GetEmailMethodResponsePort getEmailMethodResponsePort;

    @InjectMocks
    private GetEmailMethodCallService getEmailMethodCallService;

    @Test
    public void testCallWithRetrive() throws Exception {

        String accountId = "testAccountId";
        String[] emailIds = new String[] { "emailId1", "emailId2" };
        AccountState accountState = new AccountState(accountId);
        EmailPort[] emails = new EmailPort[] { new EmailAdapter(null), new EmailAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(emailIds);
        when(emailRepository.retrive(any())).thenReturn(new RetrivedEntity<>(emails, new String[] {}));
        when(getEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailPropertiesFilter.filter(any(), any(), any())).thenReturn(emails);
        when(getEmailMethodResponseBuilderPort.reset()).thenReturn(getEmailMethodResponseBuilderPort);

        when(getEmailMethodResponseBuilderPort.list(any())).thenReturn(getEmailMethodResponseBuilderPort);

        when(getEmailMethodResponseBuilderPort.notFound(any())).thenReturn(getEmailMethodResponseBuilderPort);

        when(getEmailMethodResponseBuilderPort.state(any())).thenReturn(getEmailMethodResponseBuilderPort);
        when(getEmailMethodResponseBuilderPort.build()).thenReturn(getEmailMethodResponsePort);

        GetEmailMethodResponsePort result = getEmailMethodCallService.call(getEmailMethodCallPort, previousResponses);

        verify(getEmailMethodResponseBuilderPort).reset();
        verify(getEmailMethodResponseBuilderPort).list(emails);
        verify(getEmailMethodResponseBuilderPort).notFound(any());
        verify(getEmailMethodResponseBuilderPort).state(accountState.state());
        verify(getEmailMethodResponseBuilderPort).build();
        verify(emailRepository).retrive(emailIds);
        assertEquals(result, getEmailMethodResponsePort);
    }

    @Test
    public void testCallWithRetriveAll() throws Exception {
        String accountId = "testAccountId";
        String[] emailIds = null;
        AccountState accountState = new AccountState(accountId);
        EmailPort[] emails = new EmailPort[] { new EmailAdapter(null), new EmailAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(emailIds);
        when(emailRepository.retriveAll(any())).thenReturn(new RetrivedEntity<>(emails, new String[] {}));
        when(getEmailMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailPropertiesFilter.filter(any(), any(), any())).thenReturn(emails);
        when(getEmailMethodResponseBuilderPort.reset()).thenReturn(getEmailMethodResponseBuilderPort);

        when(getEmailMethodResponseBuilderPort.list(any())).thenReturn(getEmailMethodResponseBuilderPort);

        when(getEmailMethodResponseBuilderPort.notFound(any())).thenReturn(getEmailMethodResponseBuilderPort);

        when(getEmailMethodResponseBuilderPort.state(any())).thenReturn(getEmailMethodResponseBuilderPort);
        when(getEmailMethodResponseBuilderPort.build()).thenReturn(getEmailMethodResponsePort);

        GetEmailMethodResponsePort result = getEmailMethodCallService.call(getEmailMethodCallPort, previousResponses);

        verify(getEmailMethodResponseBuilderPort).reset();
        verify(getEmailMethodResponseBuilderPort).list(emails);
        verify(getEmailMethodResponseBuilderPort).notFound(any());
        verify(getEmailMethodResponseBuilderPort).state(accountState.state());
        verify(getEmailMethodResponseBuilderPort).build();
        verify(emailRepository).retriveAll(accountId);
        assertEquals(result, getEmailMethodResponsePort);
    }

    @Test
    public void testCallWithAccountNotFoundException() throws Exception {
        String accountId = "testAccountId";

        when(accountStateRepository.retrive(accountId)).thenThrow(new AccountNotFoundException());
        when(getEmailMethodCallPort.accountId()).thenReturn(accountId);

        assertThrows(AccountNotFoundException.class,
                () -> getEmailMethodCallService.call(getEmailMethodCallPort, previousResponses));
    }

}