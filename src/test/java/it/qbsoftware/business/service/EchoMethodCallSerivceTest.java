package it.qbsoftware.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import it.qbsoftware.business.services.EchoMethodCallSerivce;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;
import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EchoMethodCallSerivceTest {

    @Mock
    private MethodResponsePort methodResponsePort;

    @Mock
    private EchoMethodCallPort echoMethodCallPort;

    @Mock
    private EchoMethodResponseBuilderPort echoMethodResponseBuilderPort;

    @InjectMocks
    private EchoMethodCallSerivce echoMethodCallSerivce;



    @Test
    public void callEchoMethodCallService() {
        String payload = "TestPayload";
        
        when(echoMethodCallPort.payload()).thenReturn(payload);
        when(echoMethodResponseBuilderPort.payload(payload)).thenReturn(echoMethodResponseBuilderPort);
        when(echoMethodResponseBuilderPort.build()).thenReturn(methodResponsePort);

        MethodResponsePort[] methodResponsePorts = echoMethodCallSerivce.call(echoMethodCallPort);

        verify(echoMethodResponseBuilderPort).payload(echoMethodCallPort.payload());
        assertEquals(1, methodResponsePorts.length);
        //TODO: attendo ale per capire i tipi
        //assertEquals(EchoMethodResponseBuilderPort.class, methodResponsePorts[0].getClass());
    }

    @Test
    public void testEchoMethodCallServiceWithEmptyPayload() {
        String payload = "";
        
        when(echoMethodCallPort.payload()).thenReturn(payload);
        when(echoMethodResponseBuilderPort.payload(payload)).thenReturn(echoMethodResponseBuilderPort);
        when(echoMethodResponseBuilderPort.build()).thenReturn(methodResponsePort);

        MethodResponsePort[] methodResponsePorts = echoMethodCallSerivce.call(echoMethodCallPort);

        verify(echoMethodResponseBuilderPort).payload(echoMethodCallPort.payload());
        assertEquals(1, methodResponsePorts.length);
        //TODO: attendo ale per capire i tipi
        //assertEquals(EchoMethodResponseBuilderPort.class, methodResponsePorts[0].getClass());
    }

}


