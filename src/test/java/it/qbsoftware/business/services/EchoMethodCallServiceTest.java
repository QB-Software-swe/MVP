package it.qbsoftware.business.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse.GetMailboxMethodResponseBuilder;

public class EchoMethodCallServiceTest {

    @Mock
    EchoMethodResponseBuilderPort echoMethodResponseBuilder;

    @Mock
    GetMailboxMethodResponseBuilder getMailboxMethodResponseBuilderPort;

    @Mock
    GetMailboxMethodResponsePort getMailboxMethodResponsePort;

    @Mock
    private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock
    EchoMethodCallPort echoMethodCallPort;
    
    @InjectMocks
    EchoMethodCallService echoMethodCallService;

    @Test
    public void testCall() {

        String payload = "testpayload";

        when(echoMethodCallPort.payload()).thenReturn(payload);
        when(echoMethodResponseBuilder.build()).thenReturn(getMailboxMethodResponsePort);

        MethodResponsePort[] methodResponsePorts = echoMethodCallService.call(echoMethodCallPort);

        verify(echoMethodResponseBuilder).payload(payload);
        verify(echoMethodResponseBuilder).build();
        assertEquals(methodResponsePorts[0], getMailboxMethodResponsePort);
        assertEquals(methodResponsePorts.length, 1);
    }
}
