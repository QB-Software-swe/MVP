package it.qbsoftware.business.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponsePort;
import it.qbsoftware.business.services.EchoMethodCallSerivce;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EchoMethodCallSerivceTest {

    @Mock
    private MethodResponsePort methodResponsePort;

    @Mock
    private EchoMethodCallPort echoMethodCallPort;

    @Mock
    private EchoMethodResponseBuilderPort echoMethodResponseBuilderPort;

    @Mock
    private EchoMethodResponsePort echoMethodResponsePort;

    @InjectMocks
    private EchoMethodCallSerivce echoMethodCallSerivce;



    @Test
    public void callEchoMethodCallService() {
        String payload = "TestPayload";
        
        when(echoMethodCallPort.payload()).thenReturn(payload);
        when(echoMethodResponseBuilderPort.payload(payload)).thenReturn(echoMethodResponseBuilderPort);
        when(echoMethodResponseBuilderPort.build()).thenReturn(echoMethodResponsePort);

        EchoMethodResponsePort result = echoMethodCallSerivce.call(echoMethodCallPort);

        verify(echoMethodResponseBuilderPort).payload(echoMethodCallPort.payload());
        assertEquals(result, echoMethodResponsePort);
    }

    @Test
    public void testEchoMethodCallServiceWithEmptyPayload() {
        String payload = "";
        
        when(echoMethodCallPort.payload()).thenReturn(payload);
        when(echoMethodResponseBuilderPort.payload(payload)).thenReturn(echoMethodResponseBuilderPort);
        when(echoMethodResponseBuilderPort.build()).thenReturn(echoMethodResponsePort);

        EchoMethodResponsePort result = echoMethodCallSerivce.call(echoMethodCallPort);

        verify(echoMethodResponseBuilderPort).payload(echoMethodCallPort.payload());
        assertEquals(result, echoMethodResponsePort);
    }

}


