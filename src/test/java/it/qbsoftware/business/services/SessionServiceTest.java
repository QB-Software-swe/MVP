package it.qbsoftware.business.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;

//FIXME: non funziona
public class SessionServiceTest {

    @Mock
    private SessionResourceBuilderPort sessionResourceBuilderPort;

    @Mock
    private UserSessionResourceRepository userSessionResourceRepository;


    @Mock
    private SessionResourcePort sessionResourcePort;


    @Mock
    EndPointConfiguration endPointConfiguration;

    @Mock
    CapabilityPort[] serverCapabilities;

    @InjectMocks
    SessionService sessionService;


    /*@Test
    public void testEmptyCall() {

        String username = "testusername";

        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), sessionService.call(username, endPointConfiguration, serverCapabilities));
    }

    @Test
    public void testPresentCall() {
        String username = "testusername";

        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.of(sessionResourcePort));
        when(sessionResourceBuilderPort.build()).thenReturn(sessionResourcePort);
    }*/

    @Test
    public void testCall() {
        String username = "testUser";

        when(sessionResourceBuilderPort.reset()).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.apiUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.uploadUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.downloadUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.eventSourceUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.capabilities(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.username(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.accounts(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.primaryAccounts(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.state(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.build()).thenReturn(sessionResourcePort);
        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.of(sessionResourcePort));

        Optional<SessionResourcePort> result = sessionService.call(username, endPointConfiguration, serverCapabilities);
        
        assertTrue(result.isPresent());
        assertEquals(sessionResourcePort, result.get());

        verify(sessionResourceBuilderPort).reset();
        verify(sessionResourceBuilderPort).apiUrl(endPointConfiguration.apiEndPoint());
        verify(sessionResourceBuilderPort).uploadUrl(endPointConfiguration.uploadEndPoint());
        verify(sessionResourceBuilderPort).downloadUrl(endPointConfiguration.downloadEndPoint());
        verify(sessionResourceBuilderPort).eventSourceUrl(endPointConfiguration.eventSourceEndPoint());
        verify(sessionResourceBuilderPort).capabilities(serverCapabilities);
        verify(sessionResourceBuilderPort).username(username);
        verify(sessionResourceBuilderPort).accounts(sessionResourcePort.accounts());
        verify(sessionResourceBuilderPort).primaryAccounts(sessionResourcePort.primaryAccounts());
        verify(sessionResourceBuilderPort).state(sessionResourcePort.state());
    
    }

}