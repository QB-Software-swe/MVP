package it.qbsoftware.business.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.eclipse.jetty.io.EndPoint;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;


public class SessionServiceTest {

    @Mock
    private SessionResourceBuilderPort sessionResourceBuilderPort;

    @Mock
    private AccountBuilderPort accountBuilderPort;

    @Mock
    private UserSessionResourceRepository userSessionResourceRepository;

    @Mock
    private SessionResourcePort sessionResourcePort;




    @Mock
    SessionResourceBuilder sessionResourceBuilder;

    @Mock
    EndPointConfiguration endPointConfiguration;

    @Mock
    CapabilityPort[] serverCapabilities;



    @InjectMocks
    SessionService sessionService;

/*

 */
    @Test
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
    }

/*
 * String username = "testUser";
        EndPointConfiguration endPointConfiguration = mock(EndPointConfiguration.class);
        CapabilityPort[] serverCapabilities = new CapabilityPort[0];
        SessionResourcePort sessionResourcePort = mock(SessionResourcePort.class);

        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.of(sessionResourcePort));
        when(sessionResourceBuilderPort.build()).thenReturn(sessionResourcePort);

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
 */


}