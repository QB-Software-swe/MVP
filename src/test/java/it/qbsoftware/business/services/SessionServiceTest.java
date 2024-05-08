package it.qbsoftware.business.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.JmapUrlConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SessionServiceTest {

    @Mock private SessionResourceBuilderPort sessionResourceBuilderPort;

    @Mock private UserSessionResourceRepository userSessionResourceRepository;

    @Mock private SessionResourcePort sessionResourcePort;

    @Mock private JmapUrlConfiguration endPointConfiguration;

    @Mock private CapabilityPort serverCapabilities;

    @InjectMocks private SessionService sessionService;

    @Test
    public void testPresentCall() {
        String username = "testUser";
        CapabilityPort[] capabilityArray = new CapabilityPort[] {serverCapabilities};

        when(sessionResourceBuilderPort.reset()).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.apiUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.uploadUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.downloadUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.eventSourceUrl(any()))
                .thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.capabilities(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.username(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.accounts(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.primaryAccounts(any()))
                .thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.state(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.build()).thenReturn(sessionResourcePort);
        when(userSessionResourceRepository.retrieve(username))
                .thenReturn(Optional.of(sessionResourcePort));

        Optional<SessionResourcePort> result =
                sessionService.call(username, endPointConfiguration, capabilityArray);

        assertTrue(result.isPresent());
        assertEquals(sessionResourcePort, result.get());

        verify(sessionResourceBuilderPort).reset();
        verify(sessionResourceBuilderPort).apiUrl(endPointConfiguration.apiUrl());
        verify(sessionResourceBuilderPort).uploadUrl(endPointConfiguration.uploadUrl());
        verify(sessionResourceBuilderPort).downloadUrl(endPointConfiguration.downloadUrl());
        verify(sessionResourceBuilderPort).eventSourceUrl(endPointConfiguration.eventSourceUrl());
        verify(sessionResourceBuilderPort).capabilities(capabilityArray);
        verify(sessionResourceBuilderPort).username(username);
        verify(sessionResourceBuilderPort).accounts(sessionResourcePort.accounts());
        verify(sessionResourceBuilderPort).primaryAccounts(sessionResourcePort.primaryAccounts());
        verify(sessionResourceBuilderPort).state(sessionResourcePort.state());
    }

    @Test
    public void testEmptyCall() {
        String username = "testUser";
        CapabilityPort[] capabilityArray = new CapabilityPort[] {serverCapabilities};

        when(sessionResourceBuilderPort.reset()).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.apiUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.uploadUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.downloadUrl(any())).thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.eventSourceUrl(any()))
                .thenReturn(sessionResourceBuilderPort);
        when(sessionResourceBuilderPort.capabilities(any())).thenReturn(sessionResourceBuilderPort);
        when(userSessionResourceRepository.retrieve(username)).thenReturn(Optional.empty());

        Optional<SessionResourcePort> result =
                sessionService.call(username, endPointConfiguration, capabilityArray);

        assertFalse(result.isPresent());

        verify(sessionResourceBuilderPort).reset();
        verify(sessionResourceBuilderPort).apiUrl(endPointConfiguration.apiUrl());
        verify(sessionResourceBuilderPort).uploadUrl(endPointConfiguration.uploadUrl());
        verify(sessionResourceBuilderPort).downloadUrl(endPointConfiguration.downloadUrl());
        verify(sessionResourceBuilderPort).eventSourceUrl(endPointConfiguration.eventSourceUrl());
        verify(sessionResourceBuilderPort).capabilities(capabilityArray);
    }
}
