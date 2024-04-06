package it.qbsoftware.business.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EchoMethodCallServiceTest {
    @Test
    public void callEchoMethodCallService() {
        //Call obj
        EchoMethodCallPort mockEchoMethodCallPort = mock(EchoMethodCallPort.class);
        when(mockEchoMethodCallPort.payload()).thenReturn("TestPyaload");

        //Response
        MethodResponsePort mockMethodResponsePort = mock(MethodResponsePort.class);

        //Return
        EchoMethodResponseBuilderPort mockEchoMethodResponseBuilderPort = mock(EchoMethodResponseBuilderPort.class);
        when(mockEchoMethodResponseBuilderPort.payload(null)).thenReturn(mockEchoMethodResponseBuilderPort);
        when(mockEchoMethodResponseBuilderPort.build()).thenReturn(mockMethodResponsePort);

        //EchoMethodCallUsecase echoMethodCallUsecase = new EchoMethodCallSerivce(mockMethodResponsePort); 

    }
}
