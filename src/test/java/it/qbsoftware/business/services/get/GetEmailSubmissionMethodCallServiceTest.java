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
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
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
    private AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    @Mock
    private InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;

    @Mock
    private InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;

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

        MethodResponsePort[] methodResponsePorts = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);

        verify(getEmailSubmissionMethodResponseBuilderPort).reset();
        verify(getEmailSubmissionMethodResponseBuilderPort).list(emailSubmissions);
        verify(getEmailSubmissionMethodResponseBuilderPort).notFound(any());
        verify(getEmailSubmissionMethodResponseBuilderPort).state(accountState.emailSubmissionState());
        verify(getEmailSubmissionMethodResponseBuilderPort).build();
        verify(emailSubmissionRepository).retrive(emailSubmissionIds);
        assertEquals(methodResponsePorts[0], getEmailSubmissionMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);
    }


    @Test
    public void testCallWithAccountNotFoundException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        String accountId = "testAccountId";

        when(accountStateRepository.retrive(accountId)).thenThrow(new AccountNotFoundException());
        when(getEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        
        MethodResponsePort[] methodResponsePorts = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof AccountNotFoundMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidResultReferenceException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        when(getReferenceIdsResolver.resolve(any(), any())).thenThrow(new InvalidResultReferenceExecption());
        
        MethodResponsePort[] methodResponsePorts = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidResultReferenceMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidArgumentsException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
       
        String[] emailSubmissionIds = new String[] { "emailSubmissionId1", "emailSubmissionId2" };
        EmailSubmissionPort[] emailSubmissions = new EmailSubmissionPort[] { new EmailSubmissionAdapter(null), new EmailSubmissionAdapter(null) };
        
        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(emailSubmissionIds);
        when(emailSubmissionPropertiesFilter.filter(any(), any())).thenThrow(new InvalidArgumentsException());
        when(emailSubmissionRepository.retrive(any())).thenReturn(new RetrivedEntity<>(emailSubmissions, new String[] {}));
        
        MethodResponsePort[] methodResponsePorts = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidArgumentsMethodErrorResponsePort);
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

        MethodResponsePort[] methodResponsePorts = getEmailSubmissionMethodCallService.call(getEmailSubmissionMethodCallPort, previousResponses);

        verify(getEmailSubmissionMethodResponseBuilderPort).reset();
        verify(getEmailSubmissionMethodResponseBuilderPort).list(emailSubmissions);
        verify(getEmailSubmissionMethodResponseBuilderPort).notFound(any());
        verify(getEmailSubmissionMethodResponseBuilderPort).state(accountState.emailSubmissionState());
        verify(getEmailSubmissionMethodResponseBuilderPort).build();
        verify(emailSubmissionRepository).retriveAll(accountId);
        assertEquals(methodResponsePorts[0], getEmailSubmissionMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);

    }


}