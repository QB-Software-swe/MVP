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
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;


public class SessionServiceTest {

    @Mock
    SessionResourceBuilder sessionResourceBuilder;

    @Mock
    EndPointConfiguration endPointConfiguration;

    @Mock
    CapabilityPort[] serverCapabilities;

    @Mock
    UserSessionResourceRepository userSessionResourceRepository;

    @InjectMocks
    SessionService sessionService;


    @Test
    public void testEmptyCall() {

        String username = "testusername";

        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), sessionService.call(username, endPointConfiguration, serverCapabilities));
    }

    @Test
    void testPresentCall() {
        String username = "testusername";
        
        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.of(sessionResourceBuilder));
    }
}
