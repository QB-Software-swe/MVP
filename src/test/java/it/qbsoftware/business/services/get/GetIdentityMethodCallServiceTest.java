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

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.IdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetIdentityMethodCallServiceTest {

    @Mock
    private AccountStateRepository accountStateRepository;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private GetIdentityMethodCallPort getIdentityMethodCallPort;

    @Mock
    private GetReferenceIdsResolver getReferenceIdsResolver;

    @Mock
    private IdentityPropertiesFilter identityPropertiesFilter;

    @Mock
    private AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort;

    @Mock
    private InvalidArgumentsMethodErrorResponsePort invalidArgumentsMethodErrorResponsePort;

    @Mock
    private InvalidResultReferenceMethodErrorResponsePort invalidResultReferenceMethodErrorResponsePort;

    @Mock
    private GetIdentityMethodResponsePort getIdentityMethodResponsePort;

    @Mock
    private GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort;

    @InjectMocks
    private GetIdentityMethodCallService getIdentityMethodCallService;


    @Test
    public void testCallWithRetrive() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {

        String accountId = "testAccountId";
        String[] identityIds = new String[] { "identityId1", "identityId2" };
        AccountState accountState = new AccountState(accountId);
        IdentityPort[] identitys = new IdentityPort[] { new IdentityAdapter(null), new IdentityAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(identityIds);
        when(identityRepository.retrive(any())).thenReturn(new RetrivedEntity<>(identitys, new String[] {}));
        when(getIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(identityPropertiesFilter.filter(any(), any())).thenReturn(identitys);
        when(getIdentityMethodResponseBuilderPort.reset()).thenReturn(getIdentityMethodResponseBuilderPort);
        
        when(getIdentityMethodResponseBuilderPort.list(any())).thenReturn(getIdentityMethodResponseBuilderPort);
        
        when(getIdentityMethodResponseBuilderPort.notFound(any())).thenReturn(getIdentityMethodResponseBuilderPort);
        
        when(getIdentityMethodResponseBuilderPort.state(any())).thenReturn(getIdentityMethodResponseBuilderPort);
        when(getIdentityMethodResponseBuilderPort.build()).thenReturn(getIdentityMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = getIdentityMethodCallService.call(getIdentityMethodCallPort, previousResponses);

        verify(getIdentityMethodResponseBuilderPort).reset();
        verify(getIdentityMethodResponseBuilderPort).list(identitys);
        verify(getIdentityMethodResponseBuilderPort).notFound(any());
        verify(getIdentityMethodResponseBuilderPort).state(accountState.identityState());
        verify(getIdentityMethodResponseBuilderPort).build();
        verify(identityRepository).retrive(identityIds);
        assertEquals(methodResponsePorts[0], getIdentityMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);
    }


    @Test
    public void testCallWithAccountNotFoundException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        String accountId = "testAccountId";

        when(accountStateRepository.retrive(accountId)).thenThrow(new AccountNotFoundException());
        when(getIdentityMethodCallPort.accountId()).thenReturn(accountId);
        
        MethodResponsePort[] methodResponsePorts = getIdentityMethodCallService.call(getIdentityMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof AccountNotFoundMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidResultReferenceException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
                
        when(getReferenceIdsResolver.resolve(any(), any())).thenThrow(new InvalidResultReferenceExecption());
        
        MethodResponsePort[] methodResponsePorts = getIdentityMethodCallService.call(getIdentityMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidResultReferenceMethodErrorResponsePort);
    }

    @Test
    public void testCallWithInvalidArgumentsException() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException {
       
        String[] identityIds = new String[] { "identityId1", "identityId2" };
        IdentityPort[] identitys = new IdentityPort[] { new IdentityAdapter(null), new IdentityAdapter(null) };
        
        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(identityIds);
        when(identityPropertiesFilter.filter(any(), any())).thenThrow(new InvalidArgumentsException());
        when(identityRepository.retrive(any())).thenReturn(new RetrivedEntity<>(identitys, new String[] {}));
        
        MethodResponsePort[] methodResponsePorts = getIdentityMethodCallService.call(getIdentityMethodCallPort, previousResponses);
        
        assertTrue(methodResponsePorts[0] instanceof InvalidArgumentsMethodErrorResponsePort);
    }



    @Test
    public void testCallWithRetriveAll() throws AccountNotFoundException, InvalidResultReferenceExecption, InvalidArgumentsException{
        String accountId = "testAccountId";
        String[] identityIds = null;
        AccountState accountState = new AccountState(accountId);
        IdentityPort[] identitys = new IdentityPort[] { new IdentityAdapter(null), new IdentityAdapter(null) };

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(identityIds);
        when(identityRepository.retriveAll(any())).thenReturn(new RetrivedEntity<>(identitys, new String[] {}));
        when(getIdentityMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(identityPropertiesFilter.filter(any(), any())).thenReturn(identitys);
        when(getIdentityMethodResponseBuilderPort.reset()).thenReturn(getIdentityMethodResponseBuilderPort);
        
        when(getIdentityMethodResponseBuilderPort.list(any())).thenReturn(getIdentityMethodResponseBuilderPort);
        
        when(getIdentityMethodResponseBuilderPort.notFound(any())).thenReturn(getIdentityMethodResponseBuilderPort);
        
        when(getIdentityMethodResponseBuilderPort.state(any())).thenReturn(getIdentityMethodResponseBuilderPort);
        when(getIdentityMethodResponseBuilderPort.build()).thenReturn(getIdentityMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = getIdentityMethodCallService.call(getIdentityMethodCallPort, previousResponses);

        verify(getIdentityMethodResponseBuilderPort).reset();
        verify(getIdentityMethodResponseBuilderPort).list(identitys);
        verify(getIdentityMethodResponseBuilderPort).notFound(any());
        verify(getIdentityMethodResponseBuilderPort).state(accountState.identityState());
        verify(getIdentityMethodResponseBuilderPort).build();
        verify(identityRepository).retriveAll(accountId);
        assertEquals(methodResponsePorts[0], getIdentityMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);

    }


}