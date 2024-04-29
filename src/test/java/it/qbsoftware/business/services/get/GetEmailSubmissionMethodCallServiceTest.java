package it.qbsoftware.business.services.get;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.EmailSubmissionPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetEmailSubmissionMethodCallServiceTest {

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private EmailSubmissionRepository emailSubmissionRepository;

    @Mock
    private GetEmailSubmissionMethodCallPort getEmailSubmissionMethodCallPort;

    @Mock
    private GetReferenceIdsResolver getReferenceIdsResolver;

    @Mock
    private EmailSubmissionPropertiesFilter emailSubmissionPropertiesFilter;

    @Mock
    private GetEmailSubmissionMethodResponsePort getEmailSubmissionMethodResponsePort;

    @Mock
    private GetEmailSubmissionMethodResponseBuilderPort getEmailSubmissionMethodResponseBuilderPort;

    @InjectMocks
    private GetEmailSubmissionMethodCallService getEmailSubmissionMethodCallService;


    @Test
    public void testCallWithRetrive() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {

        String accountId = "testAccountId";
        String[] emailSubmissionIds = new String[] { "emailSubmissionId1", "emailSubmissionId2" };
        AccountState accountState = new AccountState(accountId);
        EmailSubmissionPort[] emailSubmissions = new EmailSubmissionPort[] { new EmailSubmissionAdapter(null), new EmailSubmissionAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(emailSubmissionIds);
        when(emailSubmissionRepository.retrive(any())).thenReturn(new RetrivedEntity<>(emailSubmissions, new String[] {}));
        when(getEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailSubmissionPropertiesFilter.filter(any(), any())).thenReturn(emailSubmissions);
        when(getEmailSubmissionMethodResponseBuilderPort.reset()).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.list(any())).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.notFound(any())).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.state(any())).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.build()).thenReturn(getEmailSubmissionMethodResponsePort);

        GetEmailSubmissionMethodResponsePort result = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);

        verify(getEmailSubmissionMethodResponseBuilderPort).reset();
        verify(getEmailSubmissionMethodResponseBuilderPort).list(emailSubmissions);
        verify(getEmailSubmissionMethodResponseBuilderPort).notFound(any());
        verify(getEmailSubmissionMethodResponseBuilderPort).state(accountState.state());
        verify(getEmailSubmissionMethodResponseBuilderPort).build();
        verify(emailSubmissionRepository).retrive(emailSubmissionIds);
        assertEquals(result, getEmailSubmissionMethodResponsePort);
    }

    @Test
    public void testCallWithRetriveAll() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException{
        String accountId = "testAccountId";
        String[] emailSubmissionIds = null;
        AccountState accountState = new AccountState(accountId);
        EmailSubmissionPort[] emailSubmissions = new EmailSubmissionPort[] { new EmailSubmissionAdapter(null), new EmailSubmissionAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(emailSubmissionIds);
        when(emailSubmissionRepository.retriveAll(any())).thenReturn(new RetrivedEntity<>(emailSubmissions, new String[] {}));
        when(getEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(emailSubmissionPropertiesFilter.filter(any(), any())).thenReturn(emailSubmissions);
        when(getEmailSubmissionMethodResponseBuilderPort.reset()).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.list(any())).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.notFound(any())).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.state(any())).thenReturn(getEmailSubmissionMethodResponseBuilderPort);
        when(getEmailSubmissionMethodResponseBuilderPort.build()).thenReturn(getEmailSubmissionMethodResponsePort);

        GetEmailSubmissionMethodResponsePort result = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);

        verify(getEmailSubmissionMethodResponseBuilderPort).reset();
        verify(getEmailSubmissionMethodResponseBuilderPort).list(emailSubmissions);
        verify(getEmailSubmissionMethodResponseBuilderPort).notFound(any());
        verify(getEmailSubmissionMethodResponseBuilderPort).state(accountState.state());
        verify(getEmailSubmissionMethodResponseBuilderPort).build();
        verify(emailSubmissionRepository).retriveAll(accountId);
        assertEquals(result, getEmailSubmissionMethodResponsePort);

    }


}